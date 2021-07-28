package org.example.op;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Flow;
import java.util.function.Function;

@RequiredArgsConstructor
public class MapOperator<T, R> implements Flow.Subscriber<T> {

    private final Flow.Subscriber<? super R> subscriber;
    private final Function<T, R> mapFunction;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T item) {
        R v = mapFunction.apply(item);
        subscriber.onNext(v);
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
