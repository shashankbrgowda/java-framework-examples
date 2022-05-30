package org.shashank.mutiny.examples.uni;

import io.smallrye.mutiny.Uni;

public class FailingUnis {
    public static void main(String[] args) {
        // Pass an exception directly:
        Uni<Integer> fail1 = Uni.createFrom().failure(new Exception("boom"));
        fail1.subscribe().with(System.out::println);
        fail1.subscribe().with(System.out::println);

        // Pass a supplier called for every subscriber:
        Uni<Integer> fail2 = Uni.createFrom().failure(() -> new Exception("boom"));
        fail2.subscribe().with(System.out::println);
        fail2.subscribe().with(System.out::println);
    }
}
