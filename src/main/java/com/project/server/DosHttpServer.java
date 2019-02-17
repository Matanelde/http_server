package com.project.server;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class DosHttpServer {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(PollFromQueue.getInstance());
        executorService.submit(new HaltListener(Arrays.asList(ServerRunnable.getInstance(), PollFromQueue.getInstance())));
        executorService.submit(ServerRunnable.getInstance());
        ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new StatisticThread(), 0, 10, TimeUnit.SECONDS);
    }
}
