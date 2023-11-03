package controller;

import model.market.Consumer;
import model.market.Market;
import model.market.Producer;

import static controller.FileHandler.*;

public class MarketHandler {
    Market market;
    ProductionHandler productionHandler;
    ConsumerHandler consumerHandler;
    public  MarketHandler(Market market, ProductionHandler productionHandler, ConsumerHandler consumerHandler){
        this.market = market;
        this.productionHandler = productionHandler;
        this.consumerHandler = consumerHandler;
    }

    public void saveMarketCurrentState(){
        boolean isSaved = writeObjectData(market);

        if (isSaved) {
            writeLogg("Current state saved.");
        }else {
            writeLogg("Failed to save current state.");
        }
    }
    public void loadMarketCurrentState(){
        Market currentState = (Market) readObjectData();

        if (currentState != null) {
            market = currentState;
            for (Producer producer : market.getProducers()) {
               productionHandler.loadEmployee(producer);
            }
            for (Consumer consumer : market.getConsumers()) {
                consumerHandler.loadConsumers(consumer);
            }
            writeLogg("Market data successfully loaded.");
        } else {
            writeLogg("Failed to load market data.");
        }
    }
}
