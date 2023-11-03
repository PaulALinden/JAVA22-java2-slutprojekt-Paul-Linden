package model.market;

import model.units.Unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static model.units.Buffer.buffer;

public class Market implements Serializable {

    private final List<Producer> producers;
    private final List<Consumer> consumers;

    public BlockingQueue<Unit> getCurrentQue() {
        return currentQue;
    }

    BlockingQueue<Unit> currentQue = buffer.getBufferQue();

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
                ", que=" + currentQue +
                '}';
    }
}
