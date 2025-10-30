package com.simulador.trabalhoSO.algoritmos;
import java.util.*;

public class NFU {
    public static int executar(List<Integer> referencias, int frames) {
        if (frames <= 0) return 0;

        Map<Integer, Integer> contador = new HashMap<>();
        List<Integer> memoria = new ArrayList<>();
        Map<Integer, Integer> tempoCarga = new HashMap<>();
        int tempoAtual = 0;
        int faltas = 0;

        for (int pagina : referencias) {
            contador.put(pagina, contador.getOrDefault(pagina, 0) + 1);

            if (memoria.contains(pagina)) continue;

            faltas++;
            if (memoria.size() < frames) {
                memoria.add(pagina);
                tempoCarga.put(pagina, tempoAtual++);
            } else {
                int vitima = memoria.get(0);
                for (int pag : memoria) {
                    int contPag = contador.get(pag);
                    int contVit = contador.get(vitima);
                    if (contPag < contVit || (contPag == contVit && tempoCarga.get(pag) < tempoCarga.get(vitima))) {
                        vitima = pag;
                    }
                }
                int indice = memoria.indexOf(vitima);
                memoria.set(indice, pagina);
                tempoCarga.remove(vitima);
                tempoCarga.put(pagina, tempoAtual++);
            }
        }
        return faltas;
    }
}
