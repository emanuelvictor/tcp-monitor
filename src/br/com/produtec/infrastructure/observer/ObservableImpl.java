package br.com.produtec.infrastructure.observer;

public class ObservableImpl implements Observable {

    Observer notification;

    @Override
    public void receiveNotification(Observer notification) {
        this.notification = notification;
        this.lerAviso();
    }

    public void lerAviso() {
        System.out.println(notification);
    }
}
