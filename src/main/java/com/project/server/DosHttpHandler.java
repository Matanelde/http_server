package com.project.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DosHttpHandler implements HttpHandler {

    public void handle(HttpExchange t) {
    	QueueHolder.getInstance().add(t);
    }
}
