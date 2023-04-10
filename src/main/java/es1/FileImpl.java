package es1;

public class FileImpl implements File {
    private String name;

    public FileImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public File[] listFiles() {
        return new File[0];
    }
}
