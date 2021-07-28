package org.example;

import org.example.op.HasOperator;
import org.example.scheduler.CanSchedule;

import java.util.concurrent.Flow;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Observable <T> extends Flow.Publisher<T>, HasOperator<T>, CanSchedule<T> {
    void subscribe(Consumer<T> consumer);
}
