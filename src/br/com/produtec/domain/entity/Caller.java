package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.observer.Observable;

public class Caller implements Observable {

    @Override
    public void receiveNotification(final String notification) {
        System.out.println(notification);
    }
}
