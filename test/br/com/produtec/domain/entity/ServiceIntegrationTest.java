package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.utils.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static br.com.produtec.Application.DEFAULT_HOST;

// Test the external services (external IP's)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Define the order to running tests.
public class ServiceIntegrationTest {

//    /**
//     * Running away before the testes
//     */
//    @Before
//    public void before() {
//
//        // Start the server to execute the br.com.produtec.integration testes
////        Server.start();
//    }
//
//    /**
//     *
//     */
//    @Test
//    public void testIsConnectedMustPass() throws IOException {
//        final Service service = new Service();
//        service.connect();
//        Assert.assertTrue(service.isConnected());
//    }
//
//    /**
//     *
//     */
//    @Test(expected = java.net.SocketTimeoutException.class)
//    public void testIsConnectedMustFail() throws IOException {
//        final Service service = new Service(4001);
//        service.connect();
//    }
//
//    /**
//     *
//     */
//    @Test
//    public void testIsConnectedWithVariousServersMustPass() throws IOException {
//        final Service service4000 = new Service();
//        service4000.connect();
//        Assert.assertTrue(service4000.isConnected());
//
//        Server.start(4001);
//        final Service service4001 = new Service(4001);
//        service4001.connect();
//        Assert.assertTrue(service4001.isConnected());
//
//        Server.start(4002);
//        final Service service4002 = new Service(4002);
//        service4002.connect();
//        Assert.assertTrue(service4002.isConnected());
//    }
//
//    /**
//     *
//     */
//    @Test(expected = java.net.SocketTimeoutException.class)
//    public void testIsConnectedWithVariousServersMustFail() throws IOException {
//        final Service service4000 = new Service();
//        service4000.connect();
//        Assert.assertTrue(service4000.isConnected());
//
//        Server.start(4001);
//        final Service service4001 = new Service(4001);
//        service4001.connect();
//        Assert.assertTrue(service4001.isConnected());
//
//        Server.start(4002);
//        final Service service4002 = new Service(4002);
//        service4002.connect();
//        Assert.assertTrue(service4002.isConnected());
//
//        // Server nonexistent
//        final Service service4003 = new Service(4003);
//        service4003.connect();
//        Assert.assertTrue(service4003.isConnected());
//    }
//
//    /**
//     *
//     */
//    @Test
//    public void testIsConnectedWithExternalAddressMustPass() throws IOException {
//        Server.stop();
//        final Service service = new Service("8.8.8.8", 443);
//        service.connect();
//        Assert.assertTrue(service.isConnected());
//    }
//
//    /**
//     *
//     */
//    @Test(expected = java.net.SocketTimeoutException.class)
//    public void testIsConnectedWithExternalAddressMustFail() throws IOException {
//        Server.stop();
//        final Service service = new Service("8.8.8.253", 443);
//        service.connect();
//    }
//
//    /**
//     *
//     */
//    @Test
//    public void testIsConnectedChangeServerMustFail() throws IOException {
//        Server.stop();
//
////        final Service service = new Service("8.8.8.8", 443);
////
////        final Service service2 = new Service("8.8.8.8", 443);
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                final SocketAddress endPoint = new InetSocketAddress("8.8.8.8" != null ? "8.8.8.8" : DEFAULT_HOST, 443);
////                final Socket socket = new Socket();
//                try {
////                    socket.connect(endPoint, (int) 1000);
//new Thread(new Teste()).start();
////                    final Socket socket = new Socket("8.8.8.8", 443);
////                    System.out.println(socket.isConnected());
//
//                } catch (final Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//
////            new Thread(() -> {
//////                while (true) {
////
////                    final SocketAddress endPoint = new InetSocketAddress("8.8.8.8" != null ? "8.8.8.8" : DEFAULT_HOST, 443);
////                    final Socket socket = new Socket();
////                    try {
////                        socket.connect(endPoint, (int) 1000);
////
////                        // Se não conectar, encerra a conexão
////                        // Handler para qualquer exceção
////                    } catch (final Exception e) {
////                        e.printStackTrace();
////                    }
////
//////                    service.connect();
//////
//////                    if (service.connected == null || service.connected != service.isConnected()) {
//////                        service.connected = service.isConnected();
//////                        service.sendNotification(service.connected ? service.host + ":" + service.port + " connected" : service.host + ":" + service.port + " not connected");
//////                    }
////
////                    // Handler de timeToNextConnection para próxima verificação
////                    try {
////                        Thread.sleep(service.timeToNextConnection);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//////                }
////            }).start();
//
////            new Thread(() -> {
////                while (true) {
////
////                    service2.connect();
////
////                    if (service2.connected == null || service2.connected != service2.isConnected()) {
////                        service2.connected = service2.isConnected();
////                        service2.sendNotification(service2.connected ? service2.host + ":" + service2.port + " connected" : service2.host + ":" + service2.port + " not connected");
////                    }
////
////                    // Handler de timeToNextConnection para próxima verificação
////                    try {
////                        Thread.sleep(service2.timeToNextConnection);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                }
////            }).start();
//
////        service.connect();
////        Assert.assertTrue(service.isConnected());
////        Server.stop();
////        service.connect();
//    }
//    class Teste implements Runnable{
//        int a;
//
//        public Teste() {
//            a = 123;
//            System.out.println("constructor");
//        }
//
//        @Override
//        public void run() {
//            System.out.println("run");
//        }
//    }

    static class MyThread implements Runnable {

        Service service;

        Thread t;

        MyThread(Service service) {
            this.service = service;
            t = new Thread(this, service.host + ":" + service.port);
            System.out.println("New thread: " + t);
            t.start();
        }

        public void run() {
            try {
                while (true) {

                    service.connect();

                    if (service.connected == null || service.connected != service.isConnected()) {
                        service.connected = service.isConnected();
                        service.sendNotification(service.connected ? service.host + ":" + service.port + " connected" : service.host + ":" + service.port + " not connected");
                    }

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(service.host + ":" + service.port + " Interrupted");
            }
            System.out.println(service.host + ":" + service.port + " exiting.");
        }
    }

    public static void main(String args[]) {


        final Service service1 = new Service("8.8.8.8", 443);
        final Service service2 = new Service("8.8.4.4", 443);
        final Service service3 = new Service("35.227.68.63", 443);
        new MyThread(service1);
        new MyThread(service2);
        new MyThread(service3);

//            try {

//                Thread.sleep(1000);

        final Service service4 = new Service("8.8.8.1", 443);
        final Service service5 = new Service("8.8.8.4", 443);
        final Service service6 = new Service("216.239.34.21", 443);


        new MyThread(service4);
        new MyThread(service5);
        new MyThread(service6);

        Server.start();
        final Service service7 = new Service("127.0.0.1", 4000);
        new MyThread(service7);

//                Thread.sleep(1000);
//                Server.stop();
//            } catch (InterruptedException e) {
////                System.out.println("Main thread Interrupted");
//            }
//            System.out.println("Main thread exiting.");
    }

}


