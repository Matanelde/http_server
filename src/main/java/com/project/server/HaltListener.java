package com.project.server;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class HaltListener implements Runnable {

     List<Stoppable> stoppables;

    public HaltListener(List<Stoppable> stoppables) {
        this.stoppables = stoppables;
    }

    @Override
    public void run() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                if ("kill".equals(input)) {
                	AtomicInteger atomicInteger = new AtomicInteger(0);
                    stoppables.stream().forEach(s-> {
                        try {
                            s.stop();
                            atomicInteger.incrementAndGet();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    System.out.println(atomicInteger.get() + " killed");
                    System.exit(0);
                }
            }
        }
    }
}
