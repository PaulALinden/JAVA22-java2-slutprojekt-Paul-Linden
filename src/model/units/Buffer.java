package model.units;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Buffer implements Serializable {

    private static volatile Buffer buffer;
    private final int maxSize;
    private final BlockingQueue<Unit> bufferQue;

    private Buffer (int maxSize){
        this.maxSize = maxSize;
        bufferQue = new ArrayBlockingQueue<>(maxSize);
    }

    public static Buffer getInstance(int maxSize) {
        if (buffer == null) {
            synchronized (Buffer.class) {
                if (buffer == null) {
                    buffer = new Buffer(maxSize);
                }
            }
        }
        return buffer;
    }
    public int getBufferSize() {
        return bufferQue.size();
    }
    public int getMaxSize() {return maxSize;}
    public synchronized void add(Unit unit) {
        bufferQue.add(unit);
        notify();
    }
    public synchronized void remove() {
        if(bufferQue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bufferQue.remove();
    }
}
