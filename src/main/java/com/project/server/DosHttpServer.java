package com.project.server;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DosHttpServer {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(PollFromQueue.getInstance());
        executorService.submit(new HaltListener(Arrays.asList(ServerRunnable.getInstance(), PollFromQueue.getInstance())));
        executorService.submit(ServerRunnable.getInstance());
    }
}
