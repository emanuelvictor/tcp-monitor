package br.com.produtec.infrastructure.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {

    /**
     *
     */
    private static Socket connection;

    /**
     *
     */
    private static ServerSocket server;

    /**
     * Start the server
     */
    public static void start() {
        // DEFAULT PORT = 4000;
        new Thread(startThreadLocalServer(4000)).start();
    }

    /**
     * Start the server
     *
     * @param port int
     */
    public static void start(final int port) {
        new Thread(startThreadLocalServer(port)).start();
    }

    /**
     * Start the a local server for the br.com.produtec.integration tests
     *
     * @param port int
     */
    private static Runnable startThreadLocalServer(final int port) {
        return () -> {
            try {
                // Reserve a port
                server = new ServerSocket(port);

                // Waiting for new connection
                connection = server.accept();

                stop();

            } catch (IOException ignored) {
            }
        };
    }

    /**
     * Stop de server
     */
    public static void stop() {
        try {
            if (connection != null)
                connection.close();
            if (server != null)
                server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
