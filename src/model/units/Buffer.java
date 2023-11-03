package model.units;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Buffer implements Serializable {
    public static volatile Buffer buffer;
    private final int maxSize;
    private static BlockingQueue<Unit> bufferQue = null;

    private Buffer(int maxSize) {
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

    public BlockingQueue<Unit> getBufferQue() {
        return bufferQue;
    }

    public void setBufferQue(BlockingQueue<Unit> que) {
        bufferQue = que;
    }

    public int getBufferSize() {
        return bufferQue.size();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public synchronized void add(Unit unit) {
        if (bufferQue.size() != maxSize) {
            bufferQue.add(unit);
            notify();
        }
    }

    public synchronized void remove() {
        System.out.println(bufferQue.size());
        if (bufferQue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
        bufferQue.remove();
    }
}
