package controller;

import model.market.Market;
import model.market.Producer;
import model.units.Buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static controller.FileHandler.writeLogg;
import static model.market.Producer.getProductionCounter;
import static model.market.Producer.setProductionCounter;

public class ProductionHandler {
    Buffer buffer;
    Market market;
    List<Producer> producers;
    List<Thread> threads;

    public ProductionHandler(Buffer buffer, Market market) {
        this.buffer = buffer;
        this.market = market;
        producers = market.getProducers();
        threads = new ArrayList<>();
    }

    public String displayWorkForceNumbers() {
        return Integer.toString(producers.size());
    }

    public void employeeAdd() {
        int employeeNumber = producers.size();
        market.addProducer(new Producer(buffer, employeeNumber));
        threads.add(new Thread(producers.get(employeeNumber)));
        threads.get(employeeNumber).start();

        writeLogg("Producer added. Production units/milli.sec: " +
                producers.get(employeeNumber).getProducingSpeed());
        writeLogg("Current producers: " + producers.size());
    }

    public void employeeSubtract() {
        if (!producers.isEmpty()) {
            producers.getLast().setRunning(false);
            producers.removeLast();
            threads.removeLast();
            writeLogg("Producer removed");
            writeLogg("Current producers: " + producers.size());
        }
    }

    public void loadEmployee(Producer producer){
        int employeeNumber = producers.size();
        producers.add(producer);
        System.out.println(producers.size());
        threads.add(new Thread(producers.get(employeeNumber)));
        threads.get(employeeNumber).start();

        writeLogg("Producer restored. Production units/milli.sec: " +
                producers.get(employeeNumber).getProducingSpeed());
        writeLogg("Current producers: " + producers.size());
    }

    public synchronized int displayBufferSize() {
        return buffer.getBufferSize();
    }

    public int retrieveBufferMaxSize() {
        return buffer.getMaxSize();
    }

    public void bufferIsLow() {
        writeLogg("Buffer is under 10%");
    }

    public void bufferIsHigh() {
        writeLogg("Buffer is over 90%");
    }

    public void productionAverage() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double average = getProductionCounter();
                average = average / producers.size();
                average = Math.round(average * 10.0) / 10.0;

                writeLogg("Average production: " + average + " units per 10 seconds");
                setProductionCounter(0);
            }
        }, 10000, 10000); // Schedule the task to run every 10 seconds
    }
}
