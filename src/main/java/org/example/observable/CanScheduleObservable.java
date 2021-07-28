package org.example.observable;

import org.example.Observable;
import org.example.ObservableFactory;
import org.example.op.FilterOperator;
import org.example.op.MapOperator;
import org.example.scheduler.CanSchedule;
import org.example.scheduler.SubscribeOn;

import java.util.concurrent.ExecutorService;

public abstract class CanScheduleObservable<T> extends CanSubscribeObservable<T> implements CanSchedule<T> {

    @Override
    public Observable<T> publishOn(ExecutorService executorService) {
        return ObservableFactory.publisher(subscriber -> executorService.execute(() -> this.subscribe(subscriber)));
    }

    @Override
    public Observable<T> subscribeOn(ExecutorService executorService) {
        final Observable<T> self = this;
        return ObservableFactory.publisher(subscriber -> self.subscribe(new SubscribeOn<>(subscriber, executorService)));
    }
}
