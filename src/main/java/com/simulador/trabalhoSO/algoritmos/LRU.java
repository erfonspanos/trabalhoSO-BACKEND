package com.simulador.trabalhoSO.algoritmos;
import java.util.*;

public class LRU {
    public static int executar(List<Integer> referencias, int frames) {
        if (frames <= 0) return 0;
        LinkedHashMap<Integer, Boolean> cache = new LinkedHashMap<>(frames, 0.75f, true);
        int faltas = 0;

        for (int pagina : referencias) {
            if (cache.containsKey(pagina)) {
                cache.get(pagina);
            } else {
                faltas++;
                if (cache.size() == frames) {
                    Iterator<Integer> it = cache.keySet().iterator();
                    it.next();
                    it.remove();
                }
                cache.put(pagina, true);
            }
        }
        return faltas;
    }
}
