package com.dapo.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.handler.LoggingHandler;

/**
 * Created by dimer on 20.10.16.
 */

@IntegrationComponentScan(value = "com.dapo.*")
public abstract class AbstractIntegrationConfig {

    private static final Logger logger = LoggerFactory.getLogger(AbstractIntegrationConfig.class);

    @Value("${spring.rabbitmq.host:localhost}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.port:5672}")
    private String rabbitmqPort;

    @Value("${spring.rabbitmq.username:guest}")
    private String rabbitmqUsername;

    @Value("${spring.rabbitmq.password:guest}")
    private String rabbitmqPassword;

    @Value("${aossia.apple.apnMode:development}")
    private String apnMode;

    @Value("${spring.application.name}")
    private String applicationName;


    /**
     * Exchange name used for common messaging to all users
     */
    @Value("${aossia.notification.notificationServiceExchange:dapo.commonMessaging}")
    String notificationServiceExchangeName;

    /**
     * Exchange name used for common messaging to all users
     */
    @Value("${aossia.notification.notificationServiceExchange:dapo.apnMessaging}")
    String apnServiceExchangeName;


    @Bean
    public ConnectionFactory connectionFactory() {

        // Default Rabbitmq parameters
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHost);

        connectionFactory.setAddresses(rabbitmqHost + ":" + rabbitmqPort);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);

        return connectionFactory;
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean(name = "apnsExchange")
    public FanoutExchange apnsExchange(AmqpAdmin amqpAdmin) {
        FanoutExchange exchange = new FanoutExchange(apnServiceExchangeName, true, false);
        amqpAdmin.declareExchange(exchange);
        return exchange;
    }


    @Bean
    public IntegrationFlow commonApiMessageChain() {
        return IntegrationFlows.from("dapo.commonMessaging")
                .transform(Transformers.toJson())
                .handle(Amqp.outboundAdapter(rabbitTemplate(connectionFactory()))
                        .mappedRequestHeaders("*")
                        .routingKeyExpression("headers['routingKey']")
                        .exchangeName(notificationServiceExchangeName)
                        .defaultDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                )
                .wireTap("logging")
                .get();
    }

    @Bean
    public IntegrationFlow apnMessageChain() {
        return IntegrationFlows.from("dapo.apnMessaging")
                .transform(Transformers.toJson())
                .handle(Amqp.outboundAdapter(rabbitTemplate(connectionFactory()))
                        .mappedRequestHeaders("*")
                        .exchangeName(apnServiceExchangeName)
                        .defaultDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                )
                .wireTap("logging")
                .get();
    }

    @Bean
    IntegrationFlow logFlow() {
        LoggingHandler loggingHandler = new LoggingHandler(LoggingHandler.Level.INFO.name());
        loggingHandler.setLoggerName(AbstractIntegrationConfig.class.getName());
        return IntegrationFlows.from("logging")
                .handle(loggingHandler)
                .get();
    }

}
