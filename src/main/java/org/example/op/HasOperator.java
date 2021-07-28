package org.example.op;

import org.example.Observable;

import java.util.function.Function;
import java.util.function.Predicate;

public interface HasOperator<T> {
    Observable<T> filter(Predicate<T> predicate);
    <R> Observable<R> map(Function<T, R> function);
}
