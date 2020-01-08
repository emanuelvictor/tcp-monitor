package br.com.produtec.tcpmonitor.domain.entity;


import br.com.produtec.tcpmonitor.infrastructure.observer.Observable;
import br.com.produtec.tcpmonitor.infrastructure.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Monitor implements Observable {

    /**
     *
     */
    private JList<Service> jList = new JList<>();

    /**
     *
     */
    private JFrame jFrame = new JFrame();

    /**
     *
     */
    private JPanel jPanel = new JPanel();

    /**
     *
     */
    private Set<Observer> services = new HashSet<>();

    /**
     *
     */
    public Monitor(final String title) {
        jFrame.setTitle(title);
        jFrame.setLocationRelativeTo(null);

        this.inflate();
    }

    /**
     * @param notification
     */
    @Override
    public void receiveNotification(final Observer notification) {

        services.add(notification);

        final Service[] myArray = new Service[this.services.size()];
        services.toArray(myArray);
        jList.setListData(myArray);
        jList.setPreferredSize(new Dimension(250, 250));
    }

    public void inflate(){
        final Service[] myArray = new Service[this.services.size()];
        services.toArray(myArray);
        jList.setListData(myArray);
        jList.setPreferredSize(new Dimension(250, 250));

        if (jPanel.getComponents() == null || jFrame.getContentPane().getComponents().length == 0) {
            jPanel = new JPanel();
            jPanel.setPreferredSize(new Dimension(250, 250));
            jPanel.add(jList);
            jFrame.getContentPane().add(jPanel);
        } else if (jPanel.getComponents() != null && jPanel.getComponents().length > 0)
            jPanel.getComponents()[0] = jList;

        jFrame.setLocationRelativeTo(null);

        jFrame.pack();
        jFrame.setVisible(true);

        jFrame.setPreferredSize(new Dimension(250, 250));
        jFrame.setVisible(true);
    }
}
