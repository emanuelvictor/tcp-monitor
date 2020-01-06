package br.com.produtec.infrastructure.observer;

public class ObserverImpl implements Observer {

    Observable observable;

    @Override
    public void sendNotification(String notification) {
        observable.receiveNotification(notification);
    }

    @Override
    public void mustNotify(Observable observable) {
        this.observable = observable;
    }
}
