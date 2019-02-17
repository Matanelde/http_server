package com.project.server;

import java.util.List;
import java.util.Scanner;

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
                    stoppables.stream().peek(s-> {
                        try {
                            s.stop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    System.exit(0);
                }
            }
        }
    }
}
