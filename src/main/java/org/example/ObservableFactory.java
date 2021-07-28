package org.example;

import org.example.observable.DefaultObservable;
import org.example.observable.DelegateObservable;

import java.util.concurrent.Flow;

public class ObservableFactory {

    public static <T> Observable<T> iter(Iterable<T> iter) {
        return new DefaultObservable<>(iter);
    }

    public static <T> Observable<T> publisher(Flow.Publisher<T> publisher) {
        return new DelegateObservable<>(publisher);
    }

}
