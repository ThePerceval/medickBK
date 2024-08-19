package ru.medicbk.demo.service;

import org.springframework.stereotype.Service;
import ru.medicbk.demo.utils.ChangeString;
import ru.medicbk.demo.utils.SimulationDelay;

@Service("Slave1Service")
public class Slave1Service implements ChangeString {

    @Override
    public String changeString(String str) {
        SimulationDelay.simulateDelay();
        return new StringBuilder(str).reverse().toString();
    }
}