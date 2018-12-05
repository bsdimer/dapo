package com.dapo.common.integration.gateways;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * Created by dimer on 16.02.18.
 */

@MessagingGateway(defaultRequestChannel = "dapo.apnMessaging")
public interface ApnMessageGateway {
    void send(@Payload Object payload, @Headers Map<String, Object> headers);
}
