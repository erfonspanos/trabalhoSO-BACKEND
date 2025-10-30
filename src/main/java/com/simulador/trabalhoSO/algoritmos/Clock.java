package com.simulador.trabalhoSO.algoritmos;
import java.util.*;

public class Clock {
    public static int executar(List<Integer> referencias, int frames) {
        if (frames <= 0) return 0;
        Integer[] memoria = new Integer[frames];
        int[] bitUso = new int[frames];
        int ponteiro = 0;
        int faltas = 0;

        for (int pagina : referencias) {
            int indice = indiceDaPagina(memoria, pagina);
            if (indice != -1) {
                bitUso[indice] = 1;
                continue;
            }

            faltas++;
            while (true) {
                if (memoria[ponteiro] == null || bitUso[ponteiro] == 0) {
                    memoria[ponteiro] = pagina;
                    bitUso[ponteiro] = 1;
                    ponteiro = (ponteiro + 1) % frames;
                    break;
                } else {
                    bitUso[ponteiro] = 0;
                    ponteiro = (ponteiro + 1) % frames;
                }
            }
        }
        return faltas;
    }

    private static int indiceDaPagina(Integer[] memoria, int pagina) {
        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] != null && memoria[i] == pagina) return i;
        }
        return -1;
    }
}
