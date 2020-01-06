package br.com.produtec.infrastructure.observer;

public class ObservableImpl implements Observable {

    String aviso;

    @Override
    public void receiveNotification(String notification) {
        this.aviso = notification;
        this.lerAviso();
    }

    public void lerAviso() {
        System.out.println(aviso);
    }
}
