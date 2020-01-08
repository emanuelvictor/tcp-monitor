package br.com.produtec.tcpmonitor.infrastructure.observer;

public class ObserverImpl implements Observer {

    Observable observable;

    @Override
    public void sendNotification(Observer notification) {
        observable.receiveNotification(notification);
    }

    @Override
    public void mustNotify(Observable observable) {
        this.observable = observable;
    }
}
