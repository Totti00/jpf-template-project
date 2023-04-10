package es1;

public interface IBoundedBuffer<Item> {
    void put(Item item) throws InterruptedException;
    Item get() throws ClosedException;
}
