package com.dapo.common.integration.gateways;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * Created by dimer on 21.10.16.
 */

@MessagingGateway(defaultRequestChannel = "dapo.sendmail")
public interface SendMailGateway {

    String send(@Header("To") String to, @Header("Subject") String subject, @Payload String body);

    String send(@Header("From") String from, @Header("To") String to, @Header("Subject") String subject, @Payload String body);

    String send(@Payload String body, @Headers Map<String, Object> headers);

}
