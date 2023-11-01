package model.units;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Buffer implements Serializable {
    int maxSize = 100;
    BlockingQueue<Unit> buffer = new ArrayBlockingQueue<>(maxSize);

    public int getBufferSize() {
        return buffer.size();
    }
    public int getMaxSize() {return maxSize;}
    public synchronized void add(Unit unit) {
        buffer.add(unit);
        notify();
        //System.out.println(buffer);
    }
    public synchronized void remove() {
        if(buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.remove();
    }
}
