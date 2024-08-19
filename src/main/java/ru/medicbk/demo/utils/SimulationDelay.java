package ru.medicbk.demo.utils;

import java.util.concurrent.ThreadLocalRandom;

public class SimulationDelay {
    public static void simulateDelay() {
        try {
            int delay = ThreadLocalRandom.current().nextInt(500, 1000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}