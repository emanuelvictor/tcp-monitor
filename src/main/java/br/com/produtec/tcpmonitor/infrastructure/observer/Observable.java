package br.com.produtec.tcpmonitor.infrastructure.observer;

public interface Observable {

    void receiveNotification(final Observer message);

}
