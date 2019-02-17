package com.project.client;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.project.server.HaltListener;
import com.project.server.StatisticThread;
import com.project.server.Stoppable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DosClientsTest {
    public static void main(String[] args) {
        System.out.print("please enter number of clients:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String numberOfClients = scanner.nextLine();
            if (NumberUtils.isDigits(numberOfClients)) {
                ExecutorService executorService = Executors.newFixedThreadPool(Integer.valueOf(numberOfClients) + 1);
                List<Stoppable> threads = new ArrayList<>();
                for (int i = 0; i < Integer.valueOf(numberOfClients); i++) {
                    DosClientThread dosClientThread = new DosClientThread(Integer.valueOf(numberOfClients));
                    executorService.submit(dosClientThread);
                    threads.add(dosClientThread);
                }
                executorService.submit(new HaltListener(threads));
            }
            ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1);
            scheduledThreadPoolExecutor.scheduleAtFixedRate(new ClientStatistic(), 0, 10, TimeUnit.SECONDS);
        }
    }
}
