package es1;

public class LatchImpl implements Latch {

    private int count;

    public LatchImpl(int count) {
        this.count = count;
    }

    @Override
    public synchronized void countDown() {
        if (count > 0) {
            count--;
            if (count == 0) {
                notifyAll();
            }
        }
    }

    @Override
    public synchronized void await() throws InterruptedException {
        while (count > 0) {
            wait();
        }
    }
}
