package com.project.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class DosClientsTest {
    public static void main(String[] args) {
        while(true) {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://10.218.101.86:8080/?clientId=3");
            try {
                HttpResponse response = client.execute(request);
                System.out.println("Response Code : "
                        + response.getStatusLine().getStatusCode());
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                System.out.println(result.append(" ").append(new Date()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
