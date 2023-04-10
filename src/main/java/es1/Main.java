package es1;

public class Main {
    public static void main(String[] args) {
        try {
            // Directory da analizzare
            File directory = ModelCheckingData.getInstance().getData();

            // Numero di sorgenti con il maggior numero di linee di codice da visualizzare
            int n = 10;

            //Numero di intervalli
            int ni = 5;

            //Numero massimo di linee di codice dell'estremo sinistro dell'ultimo intervallo
            int maxl = 170;

//        File dir = new File(args[0]);
//        int numOutputFiles = Integer.parseInt(args[1]);
//        int numIntervals = Integer.parseInt(args[2]);
//        int numMaxExtremeLeftLastInterval = Integer.parseInt(args[3]);

            Master master = new Master(directory, n, ni, maxl);
//          Master master = new Master(dir, numOutputFiles, numIntervals, numMaxExtremeLeftLastInterval);
            master.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}