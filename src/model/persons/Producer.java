package model.persons;

import model.units.Buffer;
import model.units.Unit;

import java.io.Serializable;
import java.util.Random;

public class Producer implements Runnable, Serializable {

    Buffer buffer = null;

    public String getId() {
        return id;
    }

    String id;
    boolean isRunning = true;
    public void setRunning(boolean running) {
        isRunning = running;
    }
    public Producer(Buffer buffer, String id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        Random random = new Random();
        int producingSpeed = random.nextInt(10) + 1;
        producingSpeed *= 1000;

        System.out.println("producingSpeed: " +producingSpeed);
        while (isRunning) {
            //System.out.println("Current buffer size: " + buffer.getBufferSize());
            try {
                Thread.sleep(producingSpeed);
                //System.out.println(id + " added unit.");
                buffer.add(new Unit(Integer.toString(buffer.getBufferSize())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
