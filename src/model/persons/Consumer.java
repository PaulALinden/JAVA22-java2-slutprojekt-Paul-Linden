package model.persons;

import model.units.Buffer;

import java.io.Serializable;
import java.util.Random;

public class Consumer implements Runnable, Serializable {

    Buffer buffer = null;
    boolean isRunning = true;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        int consumingSpeed = random.nextInt(10) + 1;
        consumingSpeed *= 1000;

        System.out.println("consumingSpeed: " +consumingSpeed);
        while (isRunning) {
            try {
                Thread.sleep(consumingSpeed);
                //System.out.println("Consumed: " + buffer.remove());
                buffer.remove();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}
