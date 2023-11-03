package model.market;

import model.units.Buffer;

import java.io.Serializable;
import java.util.Random;

public class Consumer implements Runnable, Serializable {

    private final Buffer buffer;
    boolean isRunning = true;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int consumingSpeed = getConsumingSpeed();

        while (isRunning) {
            try {
                //noinspection BusyWait
                Thread.sleep(consumingSpeed);
                if (buffer.getBufferSize() != 0) {
                    buffer.remove();
                }
            } catch (InterruptedException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
    }

    private static int getConsumingSpeed() {
        Random random = new Random();
        int consumingSpeed = random.nextInt(10) + 1;
        consumingSpeed *= 1000;
        return consumingSpeed;
    }
}
