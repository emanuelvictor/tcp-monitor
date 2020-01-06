package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.utils.Server;

public class Monitor {

    public static void main(final String[] args) {

        final Caller caller = new Caller();

        final Service service1 = new Service("8.8.8.8", 443, caller);
        service1.start();
        final Service service2 = new Service("8.8.4.4", 443, caller);
        service2.start();
        final Service service3 = new Service("35.227.68.63", 443, caller);
        service3.start();

        final Service service4 = new Service("8.8.8.1", 443, caller);
        service4.start();
        final Service service5 = new Service("8.8.8.4", 443, caller);
        service5.start();
        final Service service6 = new Service("216.239.34.21", 443, caller);
        service6.start();


        Server.start();
        final Service service7 = new Service("127.0.0.1", 4000, caller);
        service7.start();

    }

}
