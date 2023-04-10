package es1;

public interface Latch {
    void countDown();
    void await() throws InterruptedException;
}
