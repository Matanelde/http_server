package com.project.server;

public class StatisticThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Integer[] arr = QueueHolder.getInstance().getSize();
		System.out.println("queue size is " + arr[0] + " added = " + arr[1] + " taken = " + arr[2]);
	}

}
