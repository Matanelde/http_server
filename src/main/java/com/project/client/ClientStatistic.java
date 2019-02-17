package com.project.client;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientStatistic implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (Map.Entry<Integer, AtomicInteger[]> e : DosClientThread.map.entrySet()) {
			System.out.println("client " + e.getKey() + " ok = " + e.getValue()[0].get() + " un = " + e.getValue()[1].get());
			e.getValue()[0].set(0);
			e.getValue()[1].set(0);
		}
	}

}
