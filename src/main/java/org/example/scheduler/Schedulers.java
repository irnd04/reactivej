package org.example.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Schedulers {

    private Schedulers() {}

    public static final ExecutorService SINGLE = Executors.newSingleThreadExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });
    public static final ExecutorService IO = Executors.newCachedThreadPool(r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });
    public static final ExecutorService CAL = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public static ExecutorService io() {
        return IO;
    }

    public static ExecutorService cal() {
        return CAL;
    }

    public static ExecutorService single() {
        return SINGLE;
    }

}
