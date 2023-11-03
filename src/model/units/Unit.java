package model.units;

import java.io.Serializable;

public class Unit implements Serializable {
    String id;

    public Unit(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
