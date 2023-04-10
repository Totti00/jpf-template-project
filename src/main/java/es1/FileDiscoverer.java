package es1;

public class FileDiscoverer extends Worker{

    private final File directory;
    private final BoundedBuffer<File> fileBuffer;
    private int nFilesFound;

    public FileDiscoverer(File directory, BoundedBuffer<File> fileBuffer) {
        super("File-Discoverer");
        this.directory = directory;
        this.fileBuffer = fileBuffer;
    }

    public void run() {
        //log("Inizio esecuzione");
        nFilesFound = 0;
        if (directory.isDirectory()) {
            explore(directory);
            fileBuffer.close();
            //log("job done - " + nFilesFound + " files found");
        }
    }

    private void explore(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                explore(file);
            } else if (file.getName().endsWith(".java")){
                try {
                    fileBuffer.put(file);
                    nFilesFound++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
