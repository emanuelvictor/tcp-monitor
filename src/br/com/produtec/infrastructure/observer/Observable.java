package br.com.produtec.infrastructure.observer;

public interface Observable {

    void receiveNotification(final Observer message);

}
