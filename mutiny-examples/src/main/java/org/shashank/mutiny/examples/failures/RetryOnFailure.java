package org.shashank.mutiny.examples.failures;

import io.smallrye.mutiny.Uni;

import java.time.Duration;

public class RetryOnFailure {
    public static void main(String[] args) {
        // with backoff application runs indefinitely... TODO:
        Uni.createFrom().failure(new Exception("Error"))
                .onFailure().retry()
                .withBackOff(Duration.ofMillis(1000))
                .atMost(2)
                .subscribe().with(System.out::println);
    }
}
