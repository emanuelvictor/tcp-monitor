package br.com.produtec.infrastructure.observer;

public interface Observer {

    void mustNotify(final Observable observable);

    void sendNotification(final String notification);
}
