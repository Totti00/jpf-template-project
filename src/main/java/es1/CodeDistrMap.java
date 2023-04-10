package es1;

import java.util.HashMap;
import java.util.Map;

public class CodeDistrMap {
    private Map<Integer, Integer> distribution;
    private int maxl, ni, linesForInterval;

    public CodeDistrMap(int ni, int maxl) {
        this.distribution = new HashMap<>();
        this.maxl = maxl;
        this.linesForInterval = maxl / (ni - 1);
        this.ni = ni;
        for (int i = 0; i < ni; i++) {
            this.distribution.put(i, 0);
        }
        this.distribution.put(ni, 0);
    }

    public synchronized void add(Integer lines) {
        for (int i=1; i < ni; i++) {
            if (lines <= ((i * this.linesForInterval)-1)) {
                this.distribution.put(i-1, this.distribution.get(i-1) + 1);
                return;
            }
        }
        this.distribution.put(ni-1, this.distribution.get(ni-1) + 1);
    }

    public synchronized Map<Integer, Integer> getDistr() {
        return this.distribution;
    }
}
