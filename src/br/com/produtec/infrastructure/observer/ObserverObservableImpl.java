package br.com.produtec.infrastructure.observer;

public class ObserverObservableImpl extends ObservableImpl implements Observer {

    Observable observable;

    @Override
    public void sendNotification(final Observer notification) {
        observable.receiveNotification(notification);
    }

    @Override
    public void mustNotify(final Observable observable) {
        this.observable = observable;
    }

    @Override
    public void lerAviso() {
        super.lerAviso();
        this.sendNotification(notification);
    }
}
