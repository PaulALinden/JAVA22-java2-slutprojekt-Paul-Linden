package controller;

import model.persons.Producer;
import model.units.Buffer;

import java.util.ArrayList;
import java.util.List;

public class ProductionHandler {
    Buffer buffer;
    List<Producer> workForce = new ArrayList<>();
    List<Thread> threads = new ArrayList<>();

    public ProductionHandler(Buffer buffer){
        this.buffer = buffer;
    }

    public String displayWorkForceNumbers(){
        return Integer.toString(workForce.size());
    }

    public void employeeAdd() {
        int employeeNumber = workForce.size();
        workForce.add(new Producer(buffer, Integer.toString(employeeNumber)));
        threads.add(new Thread(workForce.get(employeeNumber)));
        threads.get(employeeNumber).start();
    }

    public void employeeSubtract() {
        if (!workForce.isEmpty()) {
            workForce.getLast().setRunning(false);
            workForce.removeLast();
            threads.removeLast();
        }
    }
}
