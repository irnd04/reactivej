package org.example.scheduler;

import org.example.Observable;

import java.util.concurrent.ExecutorService;

public interface CanSchedule<T> {
    Observable<T> publishOn(ExecutorService executorService);
    Observable<T> subscribeOn(ExecutorService executorService);
}
