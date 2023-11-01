package controller;

import model.market.Consumer;
import model.market.Market;
import model.units.Buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsumerHandler {

    public static void generateConsumers(Market market, Buffer buffer) {

        //Create random amount of consumers from 3-15.
        Random random = new Random();
        int amountOfConsumers = random.nextInt(13) + 3;
        //Collect consumers and threads
        List<Consumer> consumers = market.getConsumers();
        List<Thread> consumersThread = new ArrayList<>();
        //Print amount of consumers
        for (int i = 0; i < amountOfConsumers; i++) {
            market.addConsumer(new Consumer(buffer));
            consumersThread.add(new Thread(consumers.get(i)));
            consumersThread.get(i).start();
        }

    }
}
