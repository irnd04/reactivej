package org.example.op;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Flow;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class FilterOperator<T> implements Flow.Subscriber<T> {

    private final Flow.Subscriber<? super T> subscriber;
    private final Predicate<T> predicate;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T item) {
        if (predicate.test(item)) {
            subscriber.onNext(item);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}
