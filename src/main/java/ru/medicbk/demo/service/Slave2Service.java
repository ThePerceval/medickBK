package ru.medicbk.demo.service;

import org.springframework.stereotype.Service;
import ru.medicbk.demo.utils.ChangeString;
import ru.medicbk.demo.utils.SimulationDelay;

@Service("Slave2Service")
public class Slave2Service implements ChangeString {

    @Override
    public String changeString(String str) {
        SimulationDelay.simulateDelay();
        return str.toUpperCase();
    }
}