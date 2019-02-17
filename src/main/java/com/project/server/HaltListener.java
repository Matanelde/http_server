package com.project.server;

import java.util.Scanner;

public class HaltListener implements Runnable {
    @Override
    public void run() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                if ("kill".equals(input)) {
                    System.exit(0);
                }
            }
        }
    }
}
