package model.units;

public class Unit {
    String id = "";

    public Unit(String id) {
        this.id=id;
    }

    @Override
    public String toString(){
        return id;
    }
}
