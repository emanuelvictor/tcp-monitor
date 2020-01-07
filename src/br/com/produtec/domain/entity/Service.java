package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.observer.Observable;
import br.com.produtec.infrastructure.observer.Observer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.util.Objects;


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
    private Boolean done;

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

        this.validateDateTimes();
        this.validateTimeouts();

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
    public Service(final String host, final int port, final Monitor monitor, final int pollingTimeout, final int connectionTimeout, final LocalDateTime initialDateTime, final LocalDateTime finalDateTime) {
        this.port = port;
        this.host = host;
        this.observable = monitor;

        this.pollingTimeout = pollingTimeout * 1000;
        this.connectionTimeout = connectionTimeout * 1000;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;

        this.validateDateTimes();
        this.validateTimeouts();

        thread = new Thread(this, host + ":" + port);
    }

    /**
     *
     */
    public void validateDateTimes() {
        if (this.finalDateTime != null && this.initialDateTime != null && this.finalDateTime.isBefore(this.initialDateTime))
            throw new RuntimeException("Invalid dates");
    }

    /**
     *
     */
    public void validateTimeouts() {
        if (this.pollingTimeout < connectionTimeout)
            throw new RuntimeException("The polling timeout must be greater than connection timeout");
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
    public void stop() throws IOException {
        done = true;
        connected = false;
        if (connection != null) {
            connection.close();
            connection = null;
        }
        if (thread != null) {
            thread.interrupt();
        }
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
     * @return
     */
    boolean isDone() {
        return this.done != null && this.done;
    }

    /**
     * @param observable
     */
    @Override
    public void mustNotify(final Observable observable) {
        this.observable = observable;
    }

    /**
     *
     */
    @Override
    public void sendNotification(final Observer notification) {
        if (this.observable != null)
            this.observable.receiveNotification(notification);
    }

    /**
     *
     */
    public void run() {
        try {

            while ((done == null || !done)) {

                connect();

                if (connected == null || connected != isConnected()) {
                    connected = isConnected();
                    sendNotification(this);
                }

                final LocalDateTime now = LocalDateTime.now();

                if (((initialDateTime == null) || now.isAfter(initialDateTime)) && (finalDateTime == null || finalDateTime.isAfter(now)))
                    done = false;
                else{
                    stop();
                    sendNotification(this);
                }

                Thread.sleep(this.pollingTimeout);
            }
        } catch (InterruptedException | IOException ignored) {
        }
    }

    /**
     * --------------------------------------------------------------------------
     * Hashcode and Equals
     * --------------------------------------------------------------------------
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return connectionTimeout == service.connectionTimeout &&
                pollingTimeout == service.pollingTimeout &&
                port == service.port &&
                initialDateTime.equals(service.initialDateTime) &&
                finalDateTime.equals(service.finalDateTime) &&
                host.equals(service.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialDateTime, finalDateTime, connectionTimeout, pollingTimeout, port, host);
    }


    @Override
    public String toString() {
        return host + ":" + port + (done != null && !done ? (connected ? " up" : " down") : " done");
    }
}
