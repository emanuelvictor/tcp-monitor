package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.observer.Observable;
import br.com.produtec.infrastructure.observer.Observer;

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

    private JPanel jPanel = new JPanel();

    /**
     *
     */
    private Set<Observer> services = new HashSet<>();

    /**
     *
     */
    private String title = "monitor";

    /**
     *
     */
    public Monitor() {
        jFrame.setLocationRelativeTo(null);
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

        jFrame.pack();
        jFrame.setTitle(title);

        jFrame.getContentPane();

        if (jFrame.getContentPane().getComponents().length == 0){
            jPanel = new JPanel();
            jPanel.setPreferredSize(new Dimension(250, 250));
            jPanel.add(jList);
            jFrame.getContentPane().add(jPanel);
        }
        else
            jPanel.getComponents()[0] = jList;

        jFrame.setVisible(true);

    }
}
