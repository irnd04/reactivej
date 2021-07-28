package org.example.observable;

import org.example.op.OperatorObserverable;

import java.util.concurrent.Flow;
import java.util.function.Consumer;

public abstract class CanSubscribeObservable<T> extends OperatorObserverable<T> {

    public void subscribe(Consumer<T> consumer) {
        this.subscribe(new Flow.Subscriber<T>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(T item) {
                consumer.accept(item);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
