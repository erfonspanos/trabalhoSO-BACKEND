package com.simulador.trabalhoSO.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulationRequest {

    private int frames;
    private String pageSequence; // ex: "7 0 1 2 0 3"

}
