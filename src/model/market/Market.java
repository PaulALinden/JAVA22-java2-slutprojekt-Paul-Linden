package model.market;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Market implements Serializable {

    private final List<Producer> producers;
    private final List<Consumer> consumers;
    public Market() {
        this.producers = new ArrayList<>();
        this.consumers = new ArrayList<>();
    }

    public void addProducer(Producer producer) {
        producers.add(producer);
    }
    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }
    public List<Producer> getProducers() {
        return producers;
    }
    public List<Consumer> getConsumers() {
        return consumers;
    }
    @Override
    public String toString() {
        return "Market{" +
                "producers=" + producers +
                ", consumers=" + consumers +
                '}';
    }
}
