package org.example.observable;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.concurrent.Flow;

@RequiredArgsConstructor
public class DefaultObservable<T> extends CanScheduleObservable<T> {

    private final Iterable<T> iter;

    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        subscriber.onSubscribe(new Flow.Subscription() {

            private int c = 0;
            private final Iterator<T> it = iter.iterator();

            @Override
            public void request(long n) {
                try {
                    while (it.hasNext()) {
                        T v = it.next();
                        subscriber.onNext(v);
                        if (++c == n) {
                            return;
                        }
                    }
                    subscriber.onComplete();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }

            @Override
            public void cancel() {
                // TODO cancel 구현
            }
        });
    }

}
