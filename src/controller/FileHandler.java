package controller;

import model.market.Market;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileHandler implements Serializable {

    public static Object readObjectData(String path) {

        Object messageObject = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            messageObject = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageObject;
    }

    public static boolean writeObjectData(Market stringObject, String path) {
        try (ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))) {
            ous.writeObject(stringObject);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static void writeLogg(String loggString) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/logg/logg.txt", true))) {
            br.write(loggString);
            br.newLine();
            br.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readLogg(JTextPane textPane) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/logg/logg.txt"))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                lines.add("");
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Reverse the order of lines
        Collections.reverse(lines);

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
            sb.append("\n");
        }

        textPane.setText(sb.toString());
    }
}
