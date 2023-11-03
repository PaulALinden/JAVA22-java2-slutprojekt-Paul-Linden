package view;

import javax.swing.*;
import java.awt.*;

import static controller.FileHandler.readLogg;

// Class that creates the gui for the text logg

public class LoggPane {

    public static JScrollPane loggPane() {
        JTextPane loggTextPane = new JTextPane();
        loggTextPane.setPreferredSize(new Dimension(400, 350));
        loggTextPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(loggTextPane);
        scrollPane.setPreferredSize(new Dimension(400, 350));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                //noinspection InfiniteLoopStatement
                while (true) {
                    readLogg(loggTextPane);
                    //noinspection BusyWait
                    Thread.sleep(1000);
                }
            }
        };

        worker.execute();

        return scrollPane;
    }
}
