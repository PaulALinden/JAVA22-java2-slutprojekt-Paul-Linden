package view;

import controller.ProductionHandler;
import model.units.Buffer;

import javax.swing.*;
import java.awt.*;

public class BufferQuantityProgressBar extends  JProgressBar{
    protected static JProgressBar bufferQuantityProgressbar(ProductionHandler productionHandler){

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true); // Display percentage
        progressBar.setValue(0);

        bufferQuantityUpdater(progressBar, productionHandler);

        return progressBar;
    }
    private static void bufferQuantityUpdater(JProgressBar progressBar, ProductionHandler productionHandler) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(productionHandler.displayBufferSize());
                        int value = progressBar.getValue();
                        if (value <= 10) {
                            progressBar.setForeground(Color.RED);
                        } else if (value >= 90) {
                            progressBar.setForeground(new Color(0, 128, 0));
                        } else {
                            progressBar.setForeground(Color.ORANGE);
                        }
                    });
                }
            }
        };
        worker.execute();
    }
}
