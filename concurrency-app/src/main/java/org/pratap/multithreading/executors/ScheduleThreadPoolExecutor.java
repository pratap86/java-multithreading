package org.pratap.multithreading.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdate implements Runnable {

    @Override
    public void run() {
        System.out.println("updating and downloading stock market related data from web ..");
    }
}

public class ScheduleThreadPoolExecutor {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(new StockMarketUpdate(), 1000, 5000, TimeUnit.MILLISECONDS);

    }
}
