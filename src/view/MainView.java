package view;

import controller.ProductionHandler;
import model.units.Buffer;

import javax.swing.*;
import java.awt.*;

import static view.BufferQuantityProgressBar.bufferQuantityProgressbar;
import static view.WorkForceAdjusterButtons.WorkForceAdjusterPanel;

public class MainView {

    public static void mainView(Buffer buffer, ProductionHandler productionHandler) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JProgressBar bufferQuantityProgressbar = bufferQuantityProgressbar(buffer);
        JPanel workForceAdjusterPanel = WorkForceAdjusterPanel(productionHandler);

        frame.setLayout(new FlowLayout());
        frame.add(bufferQuantityProgressbar);
        frame.add(workForceAdjusterPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
