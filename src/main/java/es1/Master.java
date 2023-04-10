package es1;

import gov.nasa.jpf.vm.Verify;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Master extends Worker {
    private final File directory;
    private final int n;
    private final int ni;
    private final int maxl;
    private CodeFreqMap codeFreqMap;
    private CodeDistrMap codeDistrMap;

    private static final int fileBufferSize = 2;

    public Master(File directory, int n, int ni, int maxl) {
        super("Master");
        this.directory = directory;
        this.n = n;
        this.ni = ni;
        this.maxl = maxl;
    }

    public void run() {
        // log("Inizio esecuzione");
        try {
            int nCodeAnalyzerAgents = 3;

            //long startTime = System.currentTimeMillis();

            this.codeFreqMap = new CodeFreqMap();
            this.codeDistrMap = new CodeDistrMap(this.ni, this.maxl);


            /* spawn Discover */
            BoundedBuffer<File> fileBuffer = new BoundedBuffer<>(fileBufferSize);
            FileDiscoverer fileDiscoverer = new FileDiscoverer(directory, fileBuffer);
            fileDiscoverer.start();

            /* spawn File Analyzer */
            Latch allFilesAnalyzed  = new LatchImpl(nCodeAnalyzerAgents);
            for (int i = 0; i < nCodeAnalyzerAgents; i++) {
                new FileAnalyzer("" + i, fileBuffer, codeFreqMap, this.codeDistrMap, allFilesAnalyzed).start();
            }

            allFilesAnalyzed.await();
            //log("All files analyzed");

            //long endTime = System.currentTimeMillis();

            /* Elab result */
            //log("Elaborating result");
            elabResult();

            //log("Printing distribution");
            //printDistr();

            //long endTime2 = System.currentTimeMillis();
            //log("Done in: " + (endTime2 - startTime) + " ms - sorting took: " + (endTime2 - endTime) + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printDistr() {
        for (int i = 0; i < (this.ni - 1); i++) {
            int start = i * (this.maxl / (this.ni - 1));
            int end = (i + 1) * (this.maxl / (this.ni - 1)) - 1;
            //System.out.println("[" + start + ", " + end + "]: " + this.codeDistrMap.getDistr().get(i));
        }
        //System.out.println("[" + this.maxl + ", infinito]: " + this.codeDistrMap.getDistr().get(this.ni-1));
    }

    private void elabResult() {
        Verify.beginAtomic();
        Set<Map.Entry<String, Integer>> set = codeFreqMap.getMap().entrySet();
        //log("dictionary size: " + set.size());
        //log("sorting...");

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(set);
        list.addAll(set);

        list.sort((Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        for (int i = 0; i < n && i < list.size(); i++) {
            String key = list.get(i).getKey();
            System.out.println(" " + (i+1) + " - " +  key + " " + list.get(i).getValue());
        }

        ModelCheckingData.getInstance().checkResults(list);

        Verify.endAtomic();
    }
}