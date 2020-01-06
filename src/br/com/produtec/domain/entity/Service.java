package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.observer.Observable;
import br.com.produtec.infrastructure.observer.Observer;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static br.com.produtec.Application.DEFAULT_HOST;
import static br.com.produtec.Application.DEFAULT_PORT;

public class Service implements Observer {

    /**
     *
     */
    private long connectionTimeout = 1000;

    /**
     *
     */
    public long timeToNextConnection = 1000;

    /**
     *
     */
    public Boolean connected;

    /**
     *
     */
    private Observable observable;

    /**
     * Port of service
     */
    public int port = DEFAULT_PORT;

    /**
     * IP Address of port
     */
    public String host = DEFAULT_HOST;

    /**
     * Connection
     */
    private Socket connection;

    /**
     *
     */
    Service() {
    }

    /**
     * @param port
     */
    Service(final int port) {
        this.port = port;
    }

    /**
     * @param port
     * @param host
     */
    Service(final String host, final int port) {
        this.port = port;
        this.host = host;
//        thread(this).run();
    }

    /**
     *
     */
    void connect() {
        final SocketAddress endPoint = new InetSocketAddress(this.host != null ? this.host : DEFAULT_HOST, this.port);
        final Socket socket = new Socket();
        try {
            socket.connect(endPoint, (int) this.connectionTimeout);

            this.connection = socket;
            // Se não conectar, encerra a conexão
            // Handler para qualquer exceção
        } catch (final Exception e) {
            if (this.connection != null && this.connection.isConnected()) {
                this.connection = null;
            }
        }
    }

    /**
     * @return
     */
    boolean isConnected() {
        return this.connection != null && this.connection.isConnected();
    }

    /**
     * @param observable
     */
    @Override
    public void mustNotify(final Observable observable) {
        this.observable = observable;
    }

    /**
     * @param notification
     */
    @Override
    public void sendNotification(final String notification) {
        System.out.println(notification);
//        this.observable.receiveNotification(notification); TODO
    }

    /**
     * Verify connection
     *
     * @return Runnable
     */
    private static Thread thread(final Service service) {


        return new Thread(() -> {
            while (true) {

                service.connect();

                if (service.connected == null || service.connected != service.isConnected()) {
                    service.connected = service.isConnected();
                    service.sendNotification(service.connected ? service.host + ":" + service.port + " connected" : service.host + ":" + service.port + " not connected");
                }

                // Handler de timeToNextConnection para próxima verificação
                try {
                    Thread.sleep(service.timeToNextConnection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

//    CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
//        // Your code here executes after 5 seconds!
//    });

}
