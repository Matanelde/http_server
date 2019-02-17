package com.project.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientThresholdChecker {
    private Map<Integer, TimeCounterData> map = new ConcurrentHashMap<>();
    private Map<Integer, Lock> locks = new ConcurrentHashMap<>();
    //this is thread safe
    private static ClientThresholdChecker instance;

    public static ClientThresholdChecker getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (ClientThresholdChecker.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new ClientThresholdChecker();
                }

            }
        }
        return instance;
    }


    private ClientThresholdChecker() {
    }

    public boolean checkAndUpdateClientThreshold(Integer clinetId) {
        TimeCounterData timeCounterData = map.computeIfAbsent(clinetId, TimeCounterData::new);
        Lock lock = locks.computeIfAbsent(clinetId, id -> new ReentrantLock());
        lock.lock();
        try {
            return check(timeCounterData, System.currentTimeMillis());
        } finally {
            lock.unlock();
        }
    }

    private boolean check(TimeCounterData timeCounterData, Long now) {
        List<Long> times = timeCounterData.getTimes();
        int size = times.size();
        if (size == 0) {
            //first time
            times.add(now);
            return true;
        } else {
            if (size < 5) {
                times.add(now);
                return true;
            } else {
                cleanTimes(times, now);
                if (times.size() < 5) {
                    times.add(now);
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private void cleanTimes(List<Long> times, Long now) {
        while (times.size() > 0 && !DosServerUtils.isLessThan5Seconds(now, times.get(0))) {
            times.remove(0);
        }
    }

}
