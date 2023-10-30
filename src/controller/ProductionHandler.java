package controller;

import model.persons.Market;
import model.persons.Producer;
import model.units.Buffer;

import java.util.ArrayList;
import java.util.List;

public class ProductionHandler {
    Buffer buffer;
    Market market;
    List<Producer> producers;
    List<Thread> threads;

    public ProductionHandler(Buffer buffer, Market market){
        this.buffer = buffer;
        this.market = market;
        producers = market.getProducers();
        threads = new ArrayList<>();
    }
    public String displayWorkForceNumbers(){
        return Integer.toString(producers.size());
    }
    public void employeeAdd() {
        int employeeNumber = producers.size();
        market.addProducer(new Producer(buffer, Integer.toString(employeeNumber)));
        threads.add(new Thread(producers.get(employeeNumber)));
        threads.get(employeeNumber).start();
    }
    public void employeeSubtract() {
        if (!producers.isEmpty()) {
            producers.getLast().setRunning(false);
            producers.removeLast();
            threads.removeLast();
        }
    }
    public int displayBufferSize(){
        return buffer.getBufferSize();
    }
}
