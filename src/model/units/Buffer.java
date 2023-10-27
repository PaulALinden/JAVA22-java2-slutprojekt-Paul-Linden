package model.units;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    Queue<Unit> buffer = new LinkedList<Unit>();
    public int getBufferSize() {
        return buffer.size();
    }
    public synchronized void add(Unit unit) {
        buffer.add(unit);
        notify();
        //System.out.println(buffer);
    }

    public synchronized Unit remove() {
        if(buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return buffer.remove();
    }
}
