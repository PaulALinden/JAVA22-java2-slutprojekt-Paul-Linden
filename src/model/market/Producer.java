package model.market;

import model.units.Buffer;
import model.units.Unit;

import java.io.Serializable;
import java.util.Random;

public class Producer extends Market implements Runnable, Serializable {

    private final Buffer buffer;
    private final String id;
    private final int producingSpeed = generateSpeed();
    private static double productionCounter;
    private boolean isRunning = true;

    public int getProducingSpeed() {return producingSpeed;}
    public String getId() {return id;}
    public void setRunning(boolean running) {
        isRunning = running;
    }
    public static double getProductionCounter() {
        return productionCounter;
    }
    public static void setProductionCounter(double productionCounter) {
        Producer.productionCounter = productionCounter;
    }

    public Producer(Buffer buffer, String id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(producingSpeed);
                buffer.add(new Unit(Integer.toString(buffer.getBufferSize())));
                productionCounter++;
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
