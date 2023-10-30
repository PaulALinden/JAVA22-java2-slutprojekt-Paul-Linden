package controller;

import model.persons.Market;
import model.persons.Producer;

import static controller.Filehandler.readData;
import static controller.Filehandler.writeData;

public class MarketHandler {

    Market market;

    public  MarketHandler(Market market){
        this.market = market;
    }

    public void saveMarketCurrentState(){
        writeData(market,"src/data/marketData.txt");
    }
    public void loadMarketCurrentState(){
        Market currentState = (Market) readData("src/data/marketData.txt");

        if (currentState != null) {
            market = currentState;
            for (Producer producer : market.getProducers()) {
                System.out.println(producer.getId());
            }
        } else {
            System.out.println("Failed to load market data.");
        }
    }
}
