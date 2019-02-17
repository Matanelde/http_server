package com.project.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerRunnable implements Runnable, Stoppable {
    HttpServer server = null;
    private static ServerRunnable instance;

    public static ServerRunnable getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (ServerRunnable.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new ServerRunnable();
                }

            }
        }
        return instance;
    }

    private ServerRunnable() {
    }

    public void stop() throws Exception{
        if (server != null) {
            server.stop(0);
        }
    }

    @Override
    public void run() {
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", new DosHttpHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
