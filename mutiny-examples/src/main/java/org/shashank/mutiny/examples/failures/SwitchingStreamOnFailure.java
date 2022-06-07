package org.shashank.mutiny.examples.failures;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public class SwitchingStreamOnFailure {
    public static void main(String[] args) {
        Uni.createFrom().failure(new Exception("Error"))
                .onFailure().recoverWithUni(Uni.createFrom().item("Shashank"))
                .subscribe().with(System.out::println);

        Multi.createFrom().failure(new Exception("Error"))
                .onFailure().recoverWithMulti(Multi.createFrom().items("A", "B", "C"))
                .subscribe().with(System.out::print);

    }
}
