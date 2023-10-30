package view;

import controller.Filehandler;
import controller.MarketHandler;
import controller.ProductionHandler;
import model.persons.Market;
import model.units.Buffer;

import javax.swing.*;
import java.awt.*;

import static view.BufferQuantityProgressBar.bufferQuantityProgressbar;
import static view.WorkForceAdjusterButtons.WorkForceAdjusterPanel;

public class MainView {

    ProductionHandler productionHandler;
    MarketHandler marketHandler;
    public MainView(ProductionHandler productionHandler, MarketHandler marketHandler){
        this.productionHandler = productionHandler;
        this.marketHandler = marketHandler;
    }

    public void mainView() {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JProgressBar bufferQuantityProgressbar = bufferQuantityProgressbar(productionHandler);
        JPanel workForceAdjusterPanel = WorkForceAdjusterPanel(productionHandler);

        JPanel fileHandling = getFileHandling();

        frame.setLayout(new FlowLayout());
        frame.add(bufferQuantityProgressbar);
        frame.add(workForceAdjusterPanel);
        frame.add(fileHandling);

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel getFileHandling() {
        JPanel fileHandling = new JPanel();
        JButton saveButton = new JButton();
        JButton loadButton = new JButton();

        saveButton.setText("Save");
        saveButton.addActionListener(e -> {
            marketHandler.saveMarketCurrentState();
        });

        loadButton.setText("Load");
        loadButton.addActionListener(e -> {
            marketHandler.loadMarketCurrentState();
        });

        fileHandling.add(saveButton);
        fileHandling.add(loadButton);
        return fileHandling;
    }
}
