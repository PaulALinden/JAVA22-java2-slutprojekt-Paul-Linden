package view;

import controller.ProductionHandler;

import javax.swing.*;
import java.awt.*;

//Class that creates tha progressbar for the realtime quantity of buffer
public class BufferQuantityProgressBar {
    static boolean isCrit = false;

    protected static JProgressBar bufferQuantityProgressbar(ProductionHandler productionHandler) {

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(productionHandler.retrieveBufferMaxSize());
        progressBar.setStringPainted(true); // Display percentage
        progressBar.setValue(0);

        bufferQuantityUpdater(progressBar, productionHandler);

        return progressBar;
    }

    private static void bufferQuantityUpdater(JProgressBar progressBar, ProductionHandler productionHandler) {
        int bufferSize = productionHandler.retrieveBufferMaxSize();
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                //noinspection InfiniteLoopStatement
                while (true) {
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(productionHandler.displayBufferSize());
                        int value = progressBar.getValue();
                        if (value <= (bufferSize * 0.1)) {
                            progressBar.setForeground(Color.RED);
                            if (!isCrit) {
                                productionHandler.bufferIsLow();
                                isCrit = true;
                            }
                        } else if (value >= (bufferSize * 0.9)) {
                            progressBar.setForeground(new Color(0, 128, 0));
                            if (!isCrit) {
                                productionHandler.bufferIsHigh();
                                isCrit = true;
                            }
                        } else {
                            progressBar.setForeground(Color.ORANGE);
                            isCrit = false;
                        }
                    });
                }
            }
        };
        worker.execute();
    }
}
