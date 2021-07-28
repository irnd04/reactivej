package org.example.scheduler;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.Future;

// TODO 이거 어떻게 구현하는거지?
@RequiredArgsConstructor
public class SubscribeOn<T> implements Flow.Subscriber<T> {

    private final Flow.Subscriber<? super T> subscriber;
    private final ExecutorService executorService;
    private final List<Future<?>> l = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T item) {
        Future<?> submit = executorService.submit(() -> subscriber.onNext(item));
        l.add(submit);
    }

    @Override
    public void onError(Throwable throwable) {
        executorService.execute(() -> subscriber.onError(throwable));
    }

    @Override
    public void onComplete() {
        try {
            for (Future<?> f : l) {
                f.get();
            }
        } catch (Throwable e) {
            executorService.execute(() -> onError(e));
            return;
        }
        executorService.execute(subscriber::onComplete);
    }

}
