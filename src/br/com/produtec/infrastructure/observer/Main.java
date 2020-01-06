package br.com.produtec.infrastructure.observer;


/**
 *
 */
public class Main {

    public static void main(String[] args) {
        final Observable general = new ObservableImpl();

        final ObserverObservableImpl coronel = new ObserverObservableImpl();
        coronel.mustNotify(general);

        final Observer sentinela = new ObserverImpl();
        sentinela.mustNotify(coronel);

    }

}
