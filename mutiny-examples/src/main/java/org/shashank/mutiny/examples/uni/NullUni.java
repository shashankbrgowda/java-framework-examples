package org.shashank.mutiny.examples.uni;

import io.smallrye.mutiny.Uni;

public class NullUni {
    public static void main(String[] args) {
        Uni<Void> uni = Uni.createFrom().nullItem();
        uni.subscribe().with(System.out::println);
    }
}
