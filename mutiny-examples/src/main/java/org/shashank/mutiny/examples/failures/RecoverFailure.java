package org.shashank.mutiny.examples.failures;

import io.smallrye.mutiny.Uni;

public class RecoverFailure {
    public static void main(String[] args) {
        Uni.createFrom().failure(new Exception("Error"))
                .onFailure().recoverWithItem(e -> getRecoveryItem())
                .subscribe().with(System.out::println);
    }

    private static String getRecoveryItem() {
        return "shashank";
    }
}
