package view;

import controller.ConsumerHandler;
import controller.MarketHandler;
import controller.ProductionHandler;

import javax.swing.*;
import java.awt.*;

import static view.BufferQuantityProgressBar.bufferQuantityProgressbar;
import static view.FileHandlingPanel.fileHandlingPanel;
import static view.LoggPane.loggPane;
import static view.ProducerAdjusterPanel.producerAdjusterPanel;

//Class that creates and handles the main GUI

public class MainView {
    ProductionHandler productionHandler;
    MarketHandler marketHandler;

    ConsumerHandler consumerHandler;

    public MainView(ProductionHandler productionHandler, MarketHandler marketHandler, ConsumerHandler consumerHandler) {
        this.productionHandler = productionHandler;
        this.marketHandler = marketHandler;
        this.consumerHandler = consumerHandler;
    }

    public void startView() {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JButton startButton = new JButton("Start");
        JButton continueButton = new JButton("Continue");

        startButton.addActionListener(e -> {

            mainView(productionHandler, marketHandler);
            consumerHandler.generateConsumers();
            productionHandler.productionAverage();
        });

        continueButton.addActionListener(e -> {
            marketHandler.loadMarketCurrentState();

            mainView(productionHandler, marketHandler);
            productionHandler.productionAverage();
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(startButton, constraints);

        constraints.gridx = 1;
        panel.add(continueButton, constraints);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private static void mainView(ProductionHandler productionHandler, MarketHandler marketHandler) {
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

        topPanel.add(createSpace());
        topPanel.add(bufferQuantityProgressbar);
        topPanel.add(createSpace());
        topPanel.add(workForceAdjusterPanel);
        topPanel.add(createSpace());

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

    private static JPanel createSpace() {
        JPanel spacing = new JPanel();
        spacing.setPreferredSize(new Dimension(0, 20));
        return spacing;
    }

}





