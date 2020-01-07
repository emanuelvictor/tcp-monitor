package br.com.produtec;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;

public class Main {
    public static void main(final String[] args) {

        final JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(250, 250));

        jPanel.add(new JLabel("Monitor name"));
        final JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 20));
        jPanel.add(textField);

        final JButton jButton = new JButton("Add service");
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final JPanel jPanelHost = new JPanel();
                jPanelHost.setPreferredSize(new Dimension(150, 50));

                jPanelHost.add(new JLabel("Host"));
                final JTextField textFieldHost = new JTextField();
                textFieldHost.setPreferredSize(new Dimension(150, 20));
                jPanelHost.add(textFieldHost);


                final JPanel jPanelPort = new JPanel();
                jPanelPort.setPreferredSize(new Dimension(150, 50));

                jPanelPort.add(new JLabel("Port"));
                final JTextField textFieldPort = new JTextField();
                textFieldPort.setPreferredSize(new Dimension(150, 20));
                jPanelPort.add(textFieldPort);

                final MaskFormatter maskData;
                final JPanel jPanelDateTimeInitial = new JPanel();
                final JPanel jPanelDateTimeFinal = new JPanel();
                try {
                    maskData = new MaskFormatter("##/##/####  ##:##");

                    jPanelDateTimeInitial.setPreferredSize(new Dimension(200, 50));
                    jPanelDateTimeInitial.add(new JLabel("Time initial"));
                    final JFormattedTextField textFieldDateTimeInitial = new JFormattedTextField();
                    maskData.install(textFieldDateTimeInitial);
                    textFieldDateTimeInitial.setPreferredSize(new Dimension(200, 20));
                    jPanelDateTimeInitial.add(textFieldDateTimeInitial);
                    textFieldDateTimeInitial.setText("04/10/1889");

                    jPanelDateTimeFinal.setPreferredSize(new Dimension(200, 50));
                    jPanelDateTimeFinal.add(new JLabel("Time final"));
                    final JFormattedTextField textFieldDateTimeFinal = new JFormattedTextField();
                    maskData.install(textFieldDateTimeFinal);
                    textFieldDateTimeFinal.setPreferredSize(new Dimension(200, 20));
                    jPanelDateTimeFinal.add(textFieldDateTimeFinal);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }


                final NumberFormat longFormat = NumberFormat.getIntegerInstance();

                final JPanel jPanelPollingTimeout = new JPanel();
                jPanelPollingTimeout.setPreferredSize(new Dimension(200, 50));
                jPanelPollingTimeout.add(new JLabel("Polling Timeout (in seconds)"));

                final NumberFormatter numberFormatterInitialDate = new NumberFormatter(longFormat);
                numberFormatterInitialDate.setValueClass(Long.class);
                numberFormatterInitialDate.setAllowsInvalid(false);
                numberFormatterInitialDate.setMinimum(0L);

                final JFormattedTextField textFieldPollingTimeout = new JFormattedTextField(numberFormatterInitialDate);
                textFieldPollingTimeout.setPreferredSize(new Dimension(200, 20));
                jPanelPollingTimeout.add(textFieldPollingTimeout);
                textFieldPollingTimeout.setText("1");


                final JPanel jPanelConnectionTimeout = new JPanel();
                jPanelConnectionTimeout.setPreferredSize(new Dimension(200, 50));
                jPanelConnectionTimeout.add(new JLabel("Connection Timeout (in seconds)"));

                final NumberFormatter numberFormatterFinalDate = new NumberFormatter(longFormat);
                numberFormatterFinalDate.setValueClass(Long.class);
                numberFormatterFinalDate.setAllowsInvalid(false);
                numberFormatterFinalDate.setMinimum(0L);

                final JFormattedTextField textFieldConnectionTimeout = new JFormattedTextField(numberFormatterInitialDate);
                textFieldConnectionTimeout.setPreferredSize(new Dimension(200, 20));
                jPanelConnectionTimeout.add(textFieldConnectionTimeout);
                textFieldConnectionTimeout.setText("1");

                final JFrame jFrame = new JFrame();
                jFrame.setTitle("Insert service");
                final JPanel container = new JPanel();
                container.setPreferredSize(new Dimension(300, 350));
                container.add(jPanelHost);
                container.add(jPanelPort);
                container.add(jPanelDateTimeInitial);
                container.add(jPanelDateTimeFinal);
                container.add(jPanelPollingTimeout);
                container.add(jPanelConnectionTimeout);
                jFrame.setContentPane(container);
                jFrame.setLocationRelativeTo(null);

                jFrame.pack();
                jFrame.setVisible(true);
            }
        });
        jPanel.add(jButton);

        final JFrame jFrame = new JFrame();
        jFrame.setTitle("Insert monitors");
        jFrame.setContentPane(jPanel);
        jFrame.setLocationRelativeTo(null);

        jFrame.pack();
        jFrame.setVisible(true);

//        final Monitor monitor = new Monitor();
//
//        final Service service1 = new Service("8.8.8.8", 443, monitor);
//        service1.start();
//        final Service service2 = new Service("8.8.4.4", 443, monitor);
//        service2.start();
//        final Service service3 = new Service("35.227.68.63", 443, monitor);
//        service3.start();
//
//
//        final Monitor monitor2 = new Monitor();
//        final Service service4 = new Service("8.8.8.1", 443, monitor2);
//        service4.start();
//        final Service service5 = new Service("8.8.8.4", 443, monitor2);
//        service5.start();
//        final Service service6 = new Service("216.239.34.21", 443, monitor2);
//        service6.start();
//
//
//        Server.start();
//        final Service service7 = new Service("127.0.0.1", 4000, monitor);
//        service7.start();

    }
}
