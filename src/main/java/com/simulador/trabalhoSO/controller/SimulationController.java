package com.simulador.trabalhoSO.controller;

import com.simulador.trabalhoSO.DTOs.SimulationRequest;
import com.simulador.trabalhoSO.DTOs.SimulationResponse;
import com.simulador.trabalhoSO.algoritmos.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SimulationController {

    @PostMapping("/simulate")
    public SimulationResponse simulate(@RequestBody SimulationRequest request) {

        // 1. Converter a string de sequência em uma Lista de Inteiros
        List<Integer> referencias = new ArrayList<>();
        String[] numeros = request.getPageSequence().trim().split("\\s+");
        for (String num : numeros) {
            if (!num.isEmpty()) {
                try {
                    referencias.add(Integer.parseInt(num));
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(e.getMessage());
                }
            }
        }

        int frames = request.getFrames();

        int fifo = FIFO.executar(referencias, frames);
        int lru = LRU.executar(referencias, frames);
        int clock = Clock.executar(referencias, frames);
        int nfu = NFU.executar(referencias, frames);

        return new SimulationResponse(fifo, lru, clock, nfu);
    }
}
