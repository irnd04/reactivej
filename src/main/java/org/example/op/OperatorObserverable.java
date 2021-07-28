package org.example.op;

import org.example.Observable;
import org.example.ObservableFactory;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class OperatorObserverable <T> implements Observable<T>, HasOperator<T> {
    @Override
    public Observable<T> filter(Predicate<T> predicate) {
        final Observable<T> self = this;
        return ObservableFactory.publisher(subscriber -> self.subscribe(new FilterOperator<>(subscriber, predicate)));
    }

    @Override
    public <R> Observable<R> map(Function<T, R> function) {
        final Observable<T> self = this;
        return ObservableFactory.publisher(subscriber -> self.subscribe(new MapOperator<>(subscriber, function)));
    }

}
