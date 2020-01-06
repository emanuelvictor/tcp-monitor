package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.utils.Server;

public class Monitor {

    public static void main(final String[] args) {


        final Service service1 = new Service("8.8.8.8", 443);
        service1.start();
        final Service service2 = new Service("8.8.4.4", 443);
        service2.start();
        final Service service3 = new Service("35.227.68.63", 443);
        service3.start();

        final Service service4 = new Service("8.8.8.1", 443);
        service4.start();
        final Service service5 = new Service("8.8.8.4", 443);
        service5.start();
        final Service service6 = new Service("216.239.34.21", 443);
        service6.start();


        Server.start();
        final Service service7 = new Service("127.0.0.1", 4000);
        service7.start();

    }

}
