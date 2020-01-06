package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.observer.Observable;
import br.com.produtec.infrastructure.observer.Observer;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.LocalDateTime;


public class Service implements Runnable, Observer {

    /**
     *
     */
    private LocalDateTime initialDateTime;

    /**
     *
     */
    private LocalDateTime finalDateTime;

    /**
     *
     */
    private long connectionTimeout = 1000;

    /**
     *
     */
    private long pollingTimeout = 1000;

    /**
     *
     */
    private Boolean connected;

    /**
     *
     */
    private Observable observable;

    /**
     * Port of service
     */
    private int port;

    /**
     * IP Address of port
     */
    private String host;

    /**
     * Connection
     */
    private Socket connection;

    /**
     *
     */
    private Thread thread;


    /**
     * @param host
     * @param port
     * @param monitor
     */
    public Service(final String host, final int port, final Monitor monitor) {
        this.port = port;
        this.host = host;
        this.observable = monitor;

        thread = new Thread(this, host + ":" + port);
    }

    /**
     * @param host
     * @param port
     * @param monitor
     * @param pollingTimeout
     * @param connectionTimeout
     * @param initialDateTime
     * @param finalDateTime
     */
    public Service(final String host, final int port, final Monitor monitor, final long pollingTimeout, final long connectionTimeout, final LocalDateTime initialDateTime, final LocalDateTime finalDateTime) {
        this.port = port;
        this.host = host;
        this.observable = monitor;

        this.pollingTimeout = pollingTimeout;
        this.connectionTimeout = connectionTimeout;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;

        thread = new Thread(this, host + ":" + port);
    }

    /**
     *
     */
    public void start() {
        thread.start();
    }

    /**
     *
     */
    void connect() {
        final SocketAddress endPoint = new InetSocketAddress(this.host, this.port);
        final Socket socket = new Socket();
        try {
            if (this.connection != null && this.connection.isConnected()) {
                this.connection.close();
            }

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
        if (this.observable != null)
            this.observable.receiveNotification(notification);
    }

    /**
     *
     */
    public void run() {
        try {
            while (true) { // mater while todo

                connect();

                if (connected == null || connected != isConnected()) {
                    connected = isConnected();
                    sendNotification(connected ? host + ":" + port + " connected" : host + ":" + port + " not connected");
                }

                Thread.sleep(this.pollingTimeout);
            }
        } catch (InterruptedException e) {
            System.out.println(host + ":" + port + " Interrupted");
        }
        System.out.println(host + ":" + port + " exiting.");
    }

}
