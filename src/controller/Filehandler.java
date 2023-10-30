package controller;

import model.persons.Market;

import java.io.*;

public class Filehandler implements Serializable{

    public static Object readData(String path) {

        Object messageObject = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            messageObject = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageObject;
    }
    public static void writeData(Market stringObject, String path) {
        try (ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))) {
            ous.writeObject(stringObject);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
