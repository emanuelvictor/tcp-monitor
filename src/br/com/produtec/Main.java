package br.com.produtec;

import br.com.produtec.domain.entity.Monitor;
import br.com.produtec.domain.entity.Service;
import br.com.produtec.infrastructure.utils.Server;

import java.time.LocalDateTime;

public class Main {
    public static void main(final String[] args) {

        final Monitor monitor = new Monitor("Monitor 1");

        final Service service1 = new Service("8.8.8.8", 443, monitor);
        service1.start();
        final Service service2 = new Service("8.8.4.4", 443, monitor);
        service2.start();
        final Service service3 = new Service("35.227.68.63", 443, monitor);
        service3.start();


        final Monitor monitor2 = new Monitor("Monitor 2");
        final Service service4 = new Service("8.8.8.1", 443, monitor2);
        service4.start();
        final Service service5 = new Service("8.8.8.4", 443, monitor2);
        service5.start();
        final Service service6 = new Service("216.239.34.21", 443, monitor2);
        service6.start();


        Server.start();
        final Service service7 = new Service("127.0.0.1", 4000, monitor);
        service7.start();


        final Monitor monitor3 = new Monitor("Monitor 3");
        final Service service8 = new Service("186.237.59.102", 443, monitor3, 30, 30, LocalDateTime.now(), LocalDateTime.now().plusMinutes(2));
        service8.start();
        final Service service9 = new Service("8.2.3.4", 443, monitor3, 30, 30, LocalDateTime.now(), LocalDateTime.now().plusMinutes(2));
        service9.start();
        final Service service10 = new Service("151.101.65.195", 443, monitor3, 30, 30, LocalDateTime.now(), LocalDateTime.now().plusMinutes(2));
        service10.start();
    }
}
