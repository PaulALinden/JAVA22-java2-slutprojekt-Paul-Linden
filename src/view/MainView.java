package view;

import controller.MarketHandler;
import controller.ProductionHandler;

import javax.swing.*;
import java.awt.*;

import static view.BufferQuantityProgressBar.bufferQuantityProgressbar;
import static view.FileHandlingPanel.fileHandlingPanel;
import static view.LoggPane.loggPane;
import static view.ProducerAdjusterPanel.producerAdjusterPanel;

public class MainView {

    ProductionHandler productionHandler;
    MarketHandler marketHandler;

    public MainView(ProductionHandler productionHandler, MarketHandler marketHandler) {
        this.productionHandler = productionHandler;
        this.marketHandler = marketHandler;
    }

    public void mainView() {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = topPanel(productionHandler);
        JPanel centerPanel = centerPanel();
        JPanel bottomPanel = bottomPanel(marketHandler);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel topPanel(ProductionHandler productionHandler) {
        JPanel topPanel = new JPanel();
        JProgressBar bufferQuantityProgressbar = bufferQuantityProgressbar(productionHandler);
        JPanel workForceAdjusterPanel = producerAdjusterPanel(productionHandler);

        workForceAdjusterPanel.setMaximumSize(new Dimension(200, 100));
        bufferQuantityProgressbar.setMaximumSize(new Dimension(200, 30));

        topPanel.add(getSpacing());
        topPanel.add(bufferQuantityProgressbar);
        topPanel.add(getSpacing());
        topPanel.add(workForceAdjusterPanel);
        topPanel.add(getSpacing());

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        return topPanel;
    }

    private static JPanel centerPanel() {
        JPanel centerPanel = new JPanel();

        centerPanel.add(loggPane());

        return centerPanel;
    }

    private static JPanel bottomPanel(MarketHandler marketHandler) {
        JPanel bottomPanel = new JPanel();

        JPanel fileHandling = fileHandlingPanel(marketHandler);
        bottomPanel.add(fileHandling);

        return bottomPanel;
    }

    private static JPanel getSpacing() {
        JPanel spacing = new JPanel();
        spacing.setPreferredSize(new Dimension(0, 20));
        return spacing;
    }
}
