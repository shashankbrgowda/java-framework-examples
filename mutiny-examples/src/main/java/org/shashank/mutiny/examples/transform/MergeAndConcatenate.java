package org.shashank.mutiny.examples.transform;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MergeAndConcatenate {
    private static List<Employee> employeeList = Arrays.asList(new Employee(1, "A", "Gowda"),
            new Employee(2, "B", "Gowda"), new Employee(3, "C", "Gowda"),
            new Employee(4, "D", "Gowda"), new Employee(5, "E", "Gowda"),
            new Employee(6, "F", "Gowda"), new Employee(7, "G", "Gowda"),
            new Employee(8, "H", "Gowda"), new Employee(9, "I", "Gowda"));

    public static void main(String[] args) {
        // https://smallrye.io/smallrye-mutiny/getting-started/transforming-items-async#transforming-items-from-multi-the-merge-vs-concatenate-dilemma

        // Simple when we use each emitted event from multi and use it for some remote call we might not get ordered
        // response based on request sent. Sometimes its necessary to order the events
        // merge - doesn't maintain order
        // concatenate - maintains order
        // both of them merges the uni into the stream in orderly or non-orderly fashion

        Multi.createFrom().items(1,2,3,4,5,6,7,8,9)
                .onItem().transformToUniAndMerge(i -> {
                    try {
                        return getEmployeeById(i);
                    } catch (InterruptedException e) {
                        return Uni.createFrom().nullItem();
                    }
                }).subscribe().with(System.out::println);

        // didn't see any difference between for this example.. both are ordered..
        System.out.println();
        Multi.createFrom().items(1,2,3,4,5,6,7,8,9)
                .onItem().transformToUniAndConcatenate(i -> {
                    try {
                        return getEmployeeById(i);
                    } catch (InterruptedException e) {
                        return Uni.createFrom().nullItem();
                    }
                }).subscribe().with(System.out::println);

    }

    private static Uni<Optional<Employee>> getEmployeeById(Integer id) throws InterruptedException {
        return Uni.createFrom()
                .item(employeeList.stream()
                        .filter(e-> Objects.equals(e.getId(), id))
                        .findAny());
    }
}
