package model.market;

import model.units.Buffer;
import model.units.Unit;

import java.io.Serializable;
import java.util.Random;

public class Producer implements Runnable, Serializable {

    private final Buffer buffer;
    private final int producingSpeed = generateSpeed();
    private static double productionCounter;
    private boolean isRunning = true;

    public int getProducingSpeed() {return producingSpeed;}

    public void setRunning(boolean running) {
        isRunning = running;
    }
    public static double getProductionCounter() {
        return productionCounter;
    }
    public static void setProductionCounter(double productionCounter) {
        Producer.productionCounter = productionCounter;
    }

    public Producer(Buffer buffer, int id) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(producingSpeed);
                if (buffer.getBufferSize() < buffer.getMaxSize()) {
                    buffer.add(new Unit(Integer.toString(buffer.getBufferSize())));
                    productionCounter++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int generateSpeed(){
        Random random = new Random();
        int speed;
        speed = random.nextInt(10) + 1;
        speed *= 1000;

        return speed;
    }
}
