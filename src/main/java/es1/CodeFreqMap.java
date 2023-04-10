package es1;

import java.util.HashMap;
import java.util.Map;

public class CodeFreqMap {
    private Map<String, Integer> map;

    public CodeFreqMap() {
        map = new HashMap<>();
    }

    public synchronized void add(String file, Integer n) {
        map.putIfAbsent(file, n);
    }

    public synchronized Map<String, Integer> getMap() {
        return map;
    }
}
