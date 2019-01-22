package com.dapo.common.jpa.config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.dapo.common.jpa.infra.SpringSecurityAuditorAware;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * Created by dimomass on 19.12.18.
 */

@EnableJpaRepositories(basePackages = {"com.dapo.common.jpa"})
@EnableTransactionManagement
@EntityScan("com.dapo.common.jpa.model")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware", dateTimeProviderRef = "dateTimeProviderAware")
public abstract class AbstractDapoJpaConfig {

    @Bean(value = "springSecurityAuditorAware")
    public AuditorAware<String> myAuditorProvider() {
        return new SpringSecurityAuditorAware();
    }

    @Bean(value = "dateTimeProviderAware")
    public DateTimeProvider dateTimeProvider() {
        return new DateTimeProvider() {
            @Override
            public Optional<TemporalAccessor> getNow() {
                return Optional.of(LocalDateTime.now());
            }
        };
    }

    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }
}
