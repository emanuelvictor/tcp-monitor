package br.com.produtec.infrastructure.suport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static br.com.produtec.Application.DEFAULT_PORT;


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
        new Thread(startThreadLocalServer(DEFAULT_PORT)).start();
    }

    /**
     * Start the server
     *
     * @param port
     */
    public static void start(final int port) {
        new Thread(startThreadLocalServer(port)).start();
    }

    /**
     * Start the a local server for the integration tests
     *
     * @param port
     */
    private static Runnable startThreadLocalServer(final int port) {
        return () -> {
            try {
                // Reserve a port
                server = new ServerSocket(port);

                // Waiting for new connection
                System.out.println("Waiting for new connection ");
                connection = server.accept();
                new Thread(server(connection)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }


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

    /**
     * @param connection
     * @return
     */
    private static Runnable server(final Socket connection) {
        return () -> {
            // Receive data
            try {

                System.out.println("Conection accepted in port: " + connection.getPort());

                // CLose the connection
                connection.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
