package org.shashank.mutiny.examples.failures;

import io.smallrye.mutiny.Multi;

// while consuming multi stream if there is a failure pipeline fails. we can ignore and send completion event on failure
public class ForceCompleteFailure {
    public static void main(String[] args) {
        Multi.createFrom().failure(new Exception("Error"))
                .onFailure().recoverWithCompletion()
                .subscribe().with(System.out::println);
        // in this case returns empty stream but in real world we'll get all successful records.
    }
}
