package com.project.client;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DosClientsTest {
    public static void main(String[] args) {
        System.out.print("please enter number of clients:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String numberOfClients = scanner.nextLine();
            if (NumberUtils.isDigits(numberOfClients)) {
                ExecutorService executorService = Executors.newFixedThreadPool(Integer.valueOf(numberOfClients));
                for (int i = 0; i < Integer.valueOf(numberOfClients); i++) {
                    DosClientThread dosClientThread = new DosClientThread(Integer.valueOf(numberOfClients));
                    executorService.submit(dosClientThread);
                }
            }
        }
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet("http://localhost:8080/?clientId=3");
//        try {
//            HttpResponse response = client.execute(request);
//            System.out.println("Response Code : "
//                    + response.getStatusLine().getStatusCode());
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(response.getEntity().getContent()));
//
//            StringBuffer result = new StringBuffer();
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//            System.out.println(result.append(" ").append(new Date()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
