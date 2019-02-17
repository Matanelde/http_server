package com.project.server;

import com.sun.net.httpserver.HttpExchange;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PollFromQueue implements Runnable {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    public PollFromQueue() {
    }

    public void run() {
        while (true) {
            try {
                HttpExchange httpExchange = DosHttpHandler.requests.take();
                DosRequestHandler dosRequestHandler = new DosRequestHandler(httpExchange);
                executor.submit(dosRequestHandler);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
