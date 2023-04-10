package es1;

public class DirectoryImpl implements File {
    private File[] files;

    public DirectoryImpl(File[] files) {
        this.files = files;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public File[] listFiles() {
        return files;
    }

    @Override
    public String getName() {
        return null;
    }
}
