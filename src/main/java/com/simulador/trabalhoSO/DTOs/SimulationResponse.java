package com.simulador.trabalhoSO.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SimulationResponse {
    private int fifo;
    private int lru;
    private int clock;
    private int nfu;
}