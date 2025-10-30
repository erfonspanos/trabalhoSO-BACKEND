package com.simulador.trabalhoSO.algoritmos;
import java.util.*;

public class FIFO {
    public static int executar(List<Integer> referencias, int frames) {
        if (frames <= 0) return 0;
        Set<Integer> memoria = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        int faltas = 0;

        for (int pagina : referencias) {
            if (memoria.contains(pagina)) continue;

            faltas++;
            if (memoria.size() < frames) {
                memoria.add(pagina);
                fila.offer(pagina);
            } else {
                int removida = fila.poll();
                memoria.remove(removida);
                memoria.add(pagina);
                fila.offer(pagina);
            }
        }
        return faltas;
    }
}