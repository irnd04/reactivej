package org.example;

import org.example.scheduler.Schedulers;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void print(Object x) {
        System.out.println(Thread.currentThread().getName() + " " + x);
    }

    // https://github.com/reactive-streams/reactive-streams-jvm
    public static void main( String[] args ) throws InterruptedException {

        ObservableFactory.iter(List.of(1, 2, 3, 4, 5, 999, 1, 3, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1))
                         .map(x -> x * x)
                         .filter(x -> x % 2 == 1)
                         .map(x -> "[" + x + "]")
                         .subscribeOn(Schedulers.cal())
                         .subscribe(App::print);

        Thread.sleep(1000);
    }
}
