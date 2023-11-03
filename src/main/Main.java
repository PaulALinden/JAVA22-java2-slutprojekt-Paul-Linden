package main;

import controller.ConsumerHandler;
import controller.FileHandler;
import controller.MarketHandler;
import controller.ProductionHandler;
import model.logg.LoggModel;
import model.market.Market;
import model.units.Buffer;
import view.MainView;

public class Main {
    public static void main(String[] args) {
        //Models
        Buffer buffer = Buffer.getInstance(100);
        Market market = new Market();
        LoggModel textLogg = new LoggModel("src/logg/logg.txt");
        LoggModel saveState = new LoggModel("src/logg/market.txt");

        //Creating Controllers and adding dependencies(Models)
        ProductionHandler productionHandler = new ProductionHandler(buffer, market);
        ConsumerHandler consumerHandler = new ConsumerHandler(market,buffer);
        MarketHandler marketHandler = new MarketHandler(market, productionHandler, consumerHandler);
        FileHandler.getInstance(textLogg,saveState);

        //Creating View and adding dependencies(Controllers)
        MainView mainView = new MainView(productionHandler, marketHandler, consumerHandler);

        //Start GUI
        javax.swing.SwingUtilities.invokeLater(mainView::startView);
    }
}