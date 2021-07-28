package org.example.observable;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Flow;

@RequiredArgsConstructor
public class DelegateObservable <T> extends CanScheduleObservable<T> {

    private final Flow.Publisher<T> publisher;

    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        publisher.subscribe(subscriber);
    }

}
