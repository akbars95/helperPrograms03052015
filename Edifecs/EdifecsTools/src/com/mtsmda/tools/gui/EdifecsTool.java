package com.mtsmda.tools.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by c-DMITMINZ on 16.07.2015.
 */
public class EdifecsTool {

    private JFrame frame;
    private JTabbedPane tabbedPane;

    private JPanel panelTM;
    private JPanel panelRRM;


    public EdifecsTool() {
        createFrame();
    }

    private void createFrame() {
        frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        createTabbedPane();

        frame.add(tabbedPane);
        frame.setPreferredSize(new Dimension(700, 350));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createTabbedPane(){
        tabbedPane = new JTabbedPane();
        tabbedPane.setSize(new Dimension(600, 350));

        panelTM = new JPanel();
        panelRRM = new JPanel();

        tabbedPane.addTab("TM", panelTM);
        tabbedPane.addTab("RRM", panelRRM);
    }

    private void createTM(){}




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EdifecsTool();
            }
        });
    }

}