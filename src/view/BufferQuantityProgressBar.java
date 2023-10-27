package view;

import model.units.Buffer;

import javax.swing.*;
import java.awt.*;

public class BufferQuantityProgressBar extends  JProgressBar{

    public static JProgressBar bufferQuantityProgressbar(Buffer buffer){

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true); // Display percentage
        progressBar.setValue(0);

        bufferQuantityUpdater(buffer, progressBar);

        return progressBar;
    }
    private static void bufferQuantityUpdater(Buffer buffer, JProgressBar progressBar) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(buffer.getBufferSize());
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
