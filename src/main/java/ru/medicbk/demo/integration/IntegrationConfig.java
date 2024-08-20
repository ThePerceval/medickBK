package ru.medicbk.demo.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@IntegrationComponentScan
@EnableIntegration
public class IntegrationConfig {
    @Bean
    DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    DirectChannel inputChannel() {
        return new DirectChannel();
    }

    @Value("${constant.service1}")
    private String service1;

    @Value("${constant.service2}")
    private String service2;

    @Value("${constant.methodName}")
    private String methodName;

    @Bean
    public IntegrationFlow processFlow() {
        AtomicInteger index = new AtomicInteger(0);
        return IntegrationFlow.from("inputChannel")
            .split()
            .<String, Boolean>route(str -> index.getAndIncrement() % 2 == 0,
                mapping -> mapping
                    .subFlowMapping(true, subFlow -> subFlow
                        .handle(service1, methodName))
                    .subFlowMapping(false, subFlow -> subFlow
                        .handle(service2, methodName)))
            .aggregate()
            .channel("outputChannel")
            .get();
    }
}