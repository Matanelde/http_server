package com.project.server;

import org.junit.Test;

public class ClientThresholdCheckerTest {

    @Test
    public void test() throws InterruptedException {
        ClientThresholdChecker clientThresholdChecker = ClientThresholdChecker.getInstance();
        System.out.println(clientThresholdChecker.checkAndUpdateClientThreshold(1));
        System.out.println(clientThresholdChecker.checkAndUpdateClientThreshold(1));
        System.out.println(clientThresholdChecker.checkAndUpdateClientThreshold(1));
        System.out.println(clientThresholdChecker.checkAndUpdateClientThreshold(1));
        System.out.println(clientThresholdChecker.checkAndUpdateClientThreshold(1));
        System.out.println(clientThresholdChecker.checkAndUpdateClientThreshold(1));
        Thread.sleep(5000);
        System.out.println(clientThresholdChecker.checkAndUpdateClientThreshold(1));

    }
}
