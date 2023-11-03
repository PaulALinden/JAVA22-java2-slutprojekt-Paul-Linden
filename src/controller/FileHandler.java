package controller;

import model.logg.LoggModel;
import model.market.Market;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Class that contains methods for reading and writing logg and programs current state

public class FileHandler implements Serializable {

    static FileHandler fileHandler;
    static LoggModel textLogg;
    static LoggModel saveState;

    public FileHandler(LoggModel textLogg, LoggModel saveState) {
        FileHandler.textLogg = textLogg;
        FileHandler.saveState = saveState;
    }

    public static void getInstance(LoggModel textLogg, LoggModel saveState) {

        if (fileHandler == null) {
            fileHandler = new FileHandler(textLogg, saveState);
        }

    }

    public static Object readObjectData() {

        Object messageObject = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveState.getFilePath()))) {
            messageObject = ois.readObject();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return messageObject;
    }

    public static boolean writeObjectData(Market stringObject) {
        try (ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(saveState.getFilePath())))) {
            ous.writeObject(stringObject);
        } catch (Exception e) {
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static void writeLogg(String loggString) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(textLogg.getFilePath(), true))) {
            br.write(loggString);
            br.newLine();
            br.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static void readLogg(JTextPane textPane) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(textLogg.getFilePath()))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                lines.add("");
                line = br.readLine();
            }
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
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
