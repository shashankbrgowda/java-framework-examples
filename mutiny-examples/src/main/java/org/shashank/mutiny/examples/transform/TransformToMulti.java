package org.shashank.mutiny.examples.transform;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransformToMulti {
    public static void main(String[] args) {
        // Useful when we have a uni of one kind and using that we'll get multiple events of same or different kind.
        Uni.createFrom().item("Shashank")
                .onItem().transformToMulti(i -> Multi.createFrom().items(getSomethingInList(i)))
                .subscribe().with(System.out::println);
    }

    private static List<String> getSomethingInList(String name) {
        return IntStream.range(1, 6).boxed().map(i -> name+i).collect(Collectors.toList());
    }
}
