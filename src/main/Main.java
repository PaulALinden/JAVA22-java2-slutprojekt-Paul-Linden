package main;

import controller.MarketHandler;
import controller.ProductionHandler;
import model.market.Market;
import model.units.Buffer;
import view.MainView;

import static controller.ConsumerHandler.generateConsumers;

public class Main {
    public static void main(String[] args) {
        //Models
        Buffer buffer = new Buffer();
        Market market = new Market();

        //Creating Controllers and adding dependencies(Models)
        ProductionHandler productionHandler = new ProductionHandler(buffer, market);
        MarketHandler marketHandler = new MarketHandler(market);

        //Creating View and adding dependencies(Controllers)
        MainView mainView = new MainView(productionHandler, marketHandler);

        //Start GUI
        javax.swing.SwingUtilities.invokeLater(mainView::mainView);


        //MOVE TO MAINVIEW !!!!!!!!!!!!!!!!!!
        //Generate consumers
        generateConsumers(market, buffer);

        //Starts average count
        productionHandler.productionAverage();
    }
}