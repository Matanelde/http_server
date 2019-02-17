package com.project.server;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DosHttpServer {
    public static void main(String[] args) throws Exception {
        PollFromQueue pollFromQueue = new PollFromQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(pollFromQueue);
        executorService.submit(new HaltListener());
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new DosHttpHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
