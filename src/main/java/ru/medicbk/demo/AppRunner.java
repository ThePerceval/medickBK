package ru.medicbk.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@IntegrationComponentScan
public class AppRunner implements CommandLineRunner {
    private final DirectChannel outputChannel;
    private final MessageChannel inputChannel;
    private final String[] strings = {"Item1", "Item2", "Item3", "Item4"};

    @Override
    public void run(String... args) {
        outputChannel.subscribe(message -> {
            List<String> payload = (List<String>) message.getPayload();
            System.out.println("Полученный payload: " + payload);
        });
        inputChannel.send(MessageBuilder.withPayload(args.length > 0 ? args : strings).build());
    }
}