package com.project.server;

import com.sun.net.httpserver.HttpExchange;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PollFromQueue implements Runnable, Stoppable {
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    private PollFromQueue() {
    }

    private static PollFromQueue instance;

    public static PollFromQueue getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (PollFromQueue.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new PollFromQueue();
                }

            }
        }
        return instance;
    }

    public void stop() throws Exception {
        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();
    }

    public void run() {
        while (true) {
            try {
                HttpExchange httpExchange = QueueHolder.getInstance().take();
                DosRequestHandler dosRequestHandler = new DosRequestHandler(httpExchange);
                executor.submit(dosRequestHandler);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
