package controller;

import model.market.Consumer;
import model.market.Market;
import model.units.Buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsumerHandler {
    Market market;
    Buffer buffer;
    List<Consumer> consumers;
    List<Thread> consumersThread;

    public ConsumerHandler(Market market, Buffer buffer) {
        this.market = market;
        this.buffer = buffer;
        consumers = market.getConsumers();
        consumersThread = new ArrayList<>();
    }

    public void generateConsumers() {
        //Create random amount of consumers from 3-15.
        Random random = new Random();
        int amountOfConsumers = random.nextInt(13) + 3;
        //Print amount of consumers
        for (int i = 0; i < amountOfConsumers; i++) {
            market.addConsumer(new Consumer(buffer));
            consumersThread.add(new Thread(consumers.get(i)));
            consumersThread.get(i).start();
        }
    }

    public void loadConsumers(Consumer consumer) {
        int consumerNumber = consumers.size();
        consumers.add(consumer);
        consumersThread.add(new Thread(consumers.get(consumerNumber)));
        consumersThread.getLast().start();
    }
}
