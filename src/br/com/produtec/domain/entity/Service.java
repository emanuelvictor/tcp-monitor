package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.observer.Observable;
import br.com.produtec.infrastructure.observer.Observer;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static br.com.produtec.Application.DEFAULT_HOST;
import static br.com.produtec.Application.DEFAULT_PORT;

public class Service implements Runnable, Observer {

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

    Thread thread;

    /**
     *
     */
    Service() {
        thread = new Thread(this, host + ":" + port);
    }

    /**
     * @param port
     */
    Service(final int port) {
        this.port = port;
        thread = new Thread(this, host + ":" + port);
    }

    /**
     * @param port
     * @param host
     */
    Service(final String host, final int port) {
        this.port = port;
        this.host = host;
        thread = new Thread(this, host + ":" + port);
    }

    /**
     *
     */
    void start(){
        thread.start();
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

    public void run() {
        try {
            while (true) {

                connect();

                if (connected == null || connected != isConnected()) {
                    connected = isConnected();
                    sendNotification(connected ? host + ":" + port + " connected" : host + ":" + port + " not connected");
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(host + ":" + port + " Interrupted");
        }
        System.out.println(host + ":" + port + " exiting.");
    }

}
