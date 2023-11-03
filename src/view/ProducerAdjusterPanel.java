package view;

import controller.ProductionHandler;

import javax.swing.*;
import java.awt.*;

//Class that creates the Panel for adjusting number of producers and displaying amount of producers active
public class ProducerAdjusterPanel {
    public static JPanel producerAdjusterPanel(ProductionHandler productionHandler) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3));

        JLabel producerLabel = new JLabel("Producers:");
        JLabel producerAmountLabel = new JLabel(productionHandler.displayWorkForceNumbers());

        producerAmountLabel.setBackground(Color.WHITE);
        producerAmountLabel.setOpaque(true);
        producerAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        producerAmountLabel.setPreferredSize(new Dimension(25, 25));

        buttonPanel.add(producerLabel);
        buttonPanel.add(producerAmountLabel);
        buttonPanel.add(addProducerButton(producerAmountLabel, productionHandler));
        buttonPanel.add(subProducerButton(producerAmountLabel, productionHandler));

        return buttonPanel;
    }

    public static JButton addProducerButton(JLabel producerAmountTextField, ProductionHandler productionHandler) {
        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            productionHandler.employeeAdd();
            producerAmountTextField.setText(productionHandler.displayWorkForceNumbers());
        });

        return addButton;
    }

    public static JButton subProducerButton(JLabel producerAmountTextField, ProductionHandler productionHandler) {
        JButton subButton = new JButton("Sub");

        subButton.addActionListener(e -> {
            productionHandler.employeeSubtract();
            producerAmountTextField.setText(productionHandler.displayWorkForceNumbers());
        });

        return subButton;
    }
}
