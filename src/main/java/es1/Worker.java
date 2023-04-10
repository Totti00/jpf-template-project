package es1;

public abstract class Worker extends Thread {
    protected Worker(String name) {
        super(name);
    }

    protected void log(String message) {
        System.out.println("[" + getName() + "] " + message);
    }
}
