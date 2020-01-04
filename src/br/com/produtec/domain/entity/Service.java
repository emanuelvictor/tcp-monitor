package br.com.produtec.domain.entity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static br.com.produtec.Application.DEFAULT_HOST;
import static br.com.produtec.Application.DEFAULT_PORT;

public class Service {

    /**
     * Port of service
     */
    private int port = DEFAULT_PORT;

    /**
     * IP Address of port
     */
    private String host = DEFAULT_HOST;

    /**
     * Connection
     */
    private Socket connection;

    /**
     *
     */
    public Service() {
    }

    /**
     * @param port
     */
    public Service(final int port) {
        this.port = port;
    }

    /**
     * @param port
     * @param host
     */
    public Service( final String host, final int port) {
        this.port = port;
        this.host = host;
    }

    /**
     * @throws IOException
     */
    public void connect() throws IOException {
        final SocketAddress endPoint = new InetSocketAddress(this.host != null ? this.host : DEFAULT_HOST, this.port);
        final Socket socket = new Socket();
        socket.connect(endPoint, 1000); // TODO colocar em uma variável global

        this.connection = socket;
    }

    /**
     * @return
     */
    public boolean isConnected() {
        return this.connection != null && this.connection.isConnected();
    }

}
