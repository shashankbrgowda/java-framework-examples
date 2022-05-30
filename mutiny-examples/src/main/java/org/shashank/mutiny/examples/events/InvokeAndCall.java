package org.shashank.mutiny.examples.events;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

/*
Invoke and call are used to peek the pipeline while processing the events and They dont have any effect on the
event itself and they wont modify the original event
 */
public class InvokeAndCall {
    public static void main(String[] args) {
        // Invoke -> Invoke is mostly used for logging - This is synchronous, return type is void
        // Call -> Closing resources and flushing - This is asynchronous, retunr type is Uni<?>

        Multi.createFrom().items("A", "B", "C")
                .onItem().transform(String::toLowerCase)
                .onItem().invoke(i -> System.out.print(i + " "))
                .subscribe().with(System.out::println);
        /*
        output:
        a a
        b b
        c c
         */

        Multi.createFrom().items("A", "B", "C")
                .onItem().transform(String::toLowerCase)
                .onItem().call(i -> Uni.createFrom().item(i))
                .subscribe().with(System.out::print);


        Multi.createFrom().items("Shashank")
                .onItem().invoke(i -> System.out.println(i))
                .onFailure().invoke(f -> System.out.println())
                .onCompletion().invoke(() -> System.out.println("Completed"))
                .onCancellation().invoke(() -> System.out.println("Cancelled"))
                .onSubscription().invoke(() -> System.out.println("Subscription"))
                .subscribe().with(System.out::println);
    }
}
