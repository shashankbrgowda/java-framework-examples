package org.shashank.mutiny.examples.uni;

import io.smallrye.mutiny.Uni;

public class UniEx {
    public static void main(String[] args) {
        //A Uni is a stream emitting either a single item or a failure.
        Uni.createFrom().item("Shashank")
                .onItem().transform(item -> item + " mutiny") // processing part of pipeline
                .onItem().transform(String::toUpperCase) // processing part of pipeline
                .subscribe().with(System.out::println); // and finally we need to subscribe for the pipeline to be completed

        // Note: if your program doesn’t do anything, verify that you didn’t forget to subscribe!

        // Mutiny APIs are not fluent and each computation stage returns a new object.

        //or

        Uni<String> uni = Uni.createFrom().item("Hello ");
        uni = uni.onItem().transform(i -> i + "World");
        uni = uni.onItem().transform(String::toUpperCase);
        uni.subscribe().with(System.out::println);
    }
}
