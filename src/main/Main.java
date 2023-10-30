package main;

import controller.Filehandler;
import controller.MarketHandler;
import controller.ProductionHandler;
import model.persons.Consumer;
import model.persons.Market;
import model.units.Buffer;
import view.MainView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Models
        Buffer buffer = new Buffer();
        Market market = new Market();

        //Creating Controllers and adding dependecies(Models)
        ProductionHandler productionHandler = new ProductionHandler(buffer, market);
        MarketHandler marketHandler = new MarketHandler(market);

        //Creating View and adding dependencies(Controllers)
        MainView mainView = new MainView(productionHandler, marketHandler);

        //Start GUI
        javax.swing.SwingUtilities.invokeLater(mainView::mainView);
        generateConsumers(market, buffer);
    }

    private static void generateConsumers(Market market, Buffer buffer) {
        //Create random amount of consumers from 3-15.
        Random random = new Random();
        int amountOfConsumers = random.nextInt(13) + 3;
        //Collect consumers and threads
        List<Consumer> consumers = market.getConsumers();
        List<Thread> consumersThread = new ArrayList<>();
        //Print amount of consumers
        for (int i = 0; i <amountOfConsumers; i++) {
            market.addConsumer(new Consumer(buffer));
            consumersThread.add(new Thread(consumers.get(i)));
            consumersThread.get(i).start();
        }
    }
}