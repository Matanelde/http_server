package com.project.server;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.impl.io.SocketOutputBuffer;

import com.sun.net.httpserver.HttpExchange;

public class QueueHolder {
    private static BlockingQueue<HttpExchange> requests = new ArrayBlockingQueue<HttpExchange>(100000);
    
    private static QueueHolder instance;
    private AtomicInteger taken = new AtomicInteger(0);
    private AtomicInteger added = new AtomicInteger(0);

    
    private QueueHolder(){}
    
    public static QueueHolder getInstance() {
    	if (instance == null) {
            //synchronized block to remove overhead
            synchronized (QueueHolder.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new QueueHolder();
                }

            }
        }
        return instance;
    }
    
    public void add(HttpExchange exchange) {
    	
    	requests.add(exchange);
    	added.incrementAndGet();
    }
    
    public HttpExchange take() throws InterruptedException {
    	taken.incrementAndGet();
    	return requests.take();
    }

    
    public Integer[] getSize() {
    	Integer[] arr = new Integer[3];
    	arr[0]=requests.size();
    	arr[1]=added.get();
    	arr[2]=taken.get();
    	added.set(0);
    	taken.set(0);
    	return arr;
    }
}
