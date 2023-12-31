package view;

import controller.MarketHandler;

import javax.swing.*;

//Class that contains the gui for saving and loading file in application
public class FileHandlingPanel {

    public static JPanel fileHandlingPanel(MarketHandler marketHandler) {
        JPanel fileHandling = new JPanel();
        JButton saveButton = new JButton();
        JButton loadButton = new JButton();

        saveButton.setText("Save");
        saveButton.addActionListener(e -> marketHandler.saveMarketCurrentState());

        loadButton.setText("Load");
        loadButton.addActionListener(e -> marketHandler.loadMarketCurrentState());

        fileHandling.add(saveButton);
        fileHandling.add(loadButton);
        return fileHandling;
    }
}
