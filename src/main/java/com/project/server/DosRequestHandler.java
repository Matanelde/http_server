package com.project.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;

public class DosRequestHandler implements Runnable {
    private HttpExchange httpExchange;
    private ClientThresholdChecker clientThresholdChecker;

    public DosRequestHandler(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        clientThresholdChecker = ClientThresholdChecker.getInstance();
    }

    public void run() {
        try {
            System.out.println("Thread is " + Thread.currentThread().getName());
            String response;
            String query = httpExchange.getRequestURI().getQuery();
            String[] parsedQuery = DosServerUtils.parseQuery(query);
            if (clientThresholdChecker.checkAndUpdateClientThreshold(Integer.valueOf(parsedQuery[1]))) {
                response = "Ok";
                httpExchange.sendResponseHeaders(200, response.length());
            } else {
                response = "Service Unavailable";
                httpExchange.sendResponseHeaders(503, response.length());
            }
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
