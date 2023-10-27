package view;

import controller.ProductionHandler;

import javax.swing.*;
import java.awt.*;

public class WorkForceAdjusterButtons extends JPanel {
    public static JPanel WorkForceAdjusterPanel(ProductionHandler productionHandler){
        JPanel buttonPanel = new JPanel();
        JTextField workForceTextField = new JTextField("0");
        workForceTextField.setPreferredSize(new Dimension(25,25));
        buttonPanel.add(addWorkForceButton(workForceTextField, productionHandler));
        buttonPanel.add(subWorkForceButton(workForceTextField, productionHandler));
        buttonPanel.add(workForceTextField);
        return buttonPanel;
    }
    public static JButton addWorkForceButton(JTextField workForceTextField, ProductionHandler productionHandler){
        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            productionHandler.employeeAdd();
            workForceTextField.setText(productionHandler.displayWorkForceNumbers());
        });

        return addButton;
    }
    public static JButton subWorkForceButton(JTextField workForceTextField, ProductionHandler productionHandler){
        JButton subButton = new JButton("Sub");

        subButton.addActionListener(e -> {
            productionHandler.employeeSubtract();
            workForceTextField.setText(productionHandler.displayWorkForceNumbers());
        });

        return subButton;
    }
}
