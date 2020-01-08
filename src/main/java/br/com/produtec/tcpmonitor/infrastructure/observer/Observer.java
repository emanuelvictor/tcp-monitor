package br.com.produtec.tcpmonitor.infrastructure.observer;

public interface Observer {

    void mustNotify(final Observable observable);

    void sendNotification(final Observer message);
}
