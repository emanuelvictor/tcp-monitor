package br.com.produtec.infrastructure.observer;

public class ObserverObservableImpl extends ObservableImpl implements Observer {

    Observable observable;

    @Override
    public void sendNotification(String notification) {
        observable.receiveNotification(notification);
    }

    @Override
    public void mustNotify(Observable observable) {
        this.observable = observable;
    }

    @Override
    public void lerAviso() {
        super.lerAviso();
        this.sendNotification(aviso);
    }
}
