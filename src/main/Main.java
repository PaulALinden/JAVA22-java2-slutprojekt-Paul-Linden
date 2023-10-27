package main;

import controller.ProductionHandler;
import model.persons.Consumer;
import model.units.Buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static view.MainView.mainView;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        ProductionHandler productionHandler = new ProductionHandler(buffer);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainView(buffer, productionHandler);
            }
        });

        Random random = new Random();
        int amountOfConsumers = random.nextInt(13) + 3;

        List<Consumer> consumers = new ArrayList<>();
        List<Thread> consumersThread = new ArrayList<>();


        for (int i = 0; i <amountOfConsumers; i++) {
            consumers.add(new Consumer(buffer));
            consumersThread.add(new Thread(consumers.get(i)));
            consumersThread.get(i).start();
        }
        System.out.println("Consumers " + consumers.size());
    }
}