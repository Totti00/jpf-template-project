package es1;

import gov.nasa.jpf.vm.Verify;

public class FileAnalyzer extends Worker{

    private final BoundedBuffer<File> fileBuffer;
    private final CodeFreqMap map;
    private final CodeDistrMap distr;
    private final Latch analyzersLatch;

    public FileAnalyzer(String id, BoundedBuffer<File> fileBuffer, CodeFreqMap map, CodeDistrMap distr, Latch analyzersLatch) throws Exception {
        super("File-Loader-" + id);
        this.fileBuffer = fileBuffer;
        this.map = map;
        this.distr = distr;
        this.analyzersLatch = analyzersLatch;
    }

    public void run() {
        //log("Inizio esecuzione");
        int nJobs = 0;
        boolean noMoreFiles = false;
        while (!noMoreFiles) {
            try {
                File file = this.fileBuffer.get();
                nJobs++;
                try {
                    this.loadFile(file);
                } catch (Exception e) {
                    //log("error in processing the " + nJobs + " doc.");
                }
            } catch (ClosedException e) {
                //log("No more files to load");
                noMoreFiles = true;
            }
        }
        this.analyzersLatch.countDown();
        //log("Fine esecuzione");
    }

    private void loadFile(File file) throws Exception {
        //log("Loading file " + file.getName());
        int linesCount = 3;
        this.map.add(file.getName(), linesCount);
        this.distr.add(linesCount);
    }
}
