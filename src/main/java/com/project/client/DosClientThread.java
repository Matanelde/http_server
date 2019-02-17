package com.project.client;

import com.project.server.Stoppable;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DosClientThread implements Runnable, Stoppable {

    private static final String SERVER_URL = "http://localhost:80/?clientId=";
    public static Map<Integer, AtomicInteger[]> map = new ConcurrentHashMap<>();
    private int numberOfClient;
    private boolean run = true;

    public DosClientThread(int numberOfClient) {
        this.numberOfClient = numberOfClient;

    }

    @Override
    public void run() {
        HttpClient client = HttpClientBuilder.create().build();
        Random random = new Random();
        while (run) {
            try {
                int clientId = random.nextInt(numberOfClient);
                HttpGet request = new HttpGet(SERVER_URL + clientId);
                HttpResponse response = client.execute(request);
                StringBuffer result = new StringBuffer();
                String line = "";
                BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                AtomicInteger[] arr = map.computeIfAbsent(clientId, id->{
                	AtomicInteger[] array = new AtomicInteger[2];
                	array[0] = new AtomicInteger(0);
                	array[1] = new AtomicInteger(0);
                	return array;
                });
                if(result!=null && result.toString().contains("Ok")) {
                	arr[0].incrementAndGet();
                }else{
                	arr[1].incrementAndGet();
                }
//                System.out.println(result.append(" " + clientId + " ").append(new Date()));
                Thread.sleep(random.nextInt(1000));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() throws Exception {
        run = false;
    }
}
