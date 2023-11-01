package controller;

import model.market.Market;
import model.market.Producer;

import static controller.FileHandler.readObjectData;
import static controller.FileHandler.writeObjectData;

public class MarketHandler {
    Market market;
    public  MarketHandler(Market market){
        this.market = market;
    }

    public void saveMarketCurrentState(){
        writeObjectData(market,"src/data/marketData.txt");
    }
    public void loadMarketCurrentState(){
        Market currentState = (Market) readObjectData("src/data/marketData.txt");

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
