package org.shashank.mutiny.examples.uni;

import io.smallrye.mutiny.Uni;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class UnisFromCompletionStage {
    public static void main(String[] args) {
        CompletionStage<String> cs = CompletableFuture.supplyAsync(() -> "Shashank");
        Uni<String> uni = Uni.createFrom().completionStage(cs);
        String str = uni.subscribe().asCompletionStage().join().toUpperCase();
        System.out.println(str);
    }
}
