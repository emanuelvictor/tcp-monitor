package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.observer.Observable;

import javax.swing.*;
import java.awt.*;

public class Monitor implements Observable {

    private JLabel jLabel;

    private JPanel jPanel;

    private JFrame frame1;

    public Monitor() {

    }

    @Override
    public void receiveNotification(final String notification) {
        System.out.println(notification);

        if (jLabel == null)
            jLabel = new JLabel(notification);
        else
            jLabel.setText(notification);

        if (jPanel == null) {
            jPanel = new JPanel();
            jPanel.add(jLabel);
        }

        if (frame1 == null)
            frame1 = new JFrame("frame1");

        frame1.getContentPane().add(jPanel);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.getBounds().setSize(new Dimension(100, 100));
        frame1.setVisible(true);

    }
}
