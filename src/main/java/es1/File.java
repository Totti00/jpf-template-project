package es1;

public interface File {
    boolean isDirectory();
    File[] listFiles();
    String getName();
}
