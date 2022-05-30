package org.shashank.mutiny.examples.transform;

import io.smallrye.mutiny.Uni;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransformToUni {
    private static List<Employee> employeeList = Arrays.asList(new Employee(1, "A", "Gowda"),
            new Employee(2, "B", "Gowda"));

    public static void main(String[] args) {
        Uni.createFrom().item(1)
                .onItem().transformToUni(i -> {
                    try {
                        // transforms and returns different Uni (Uni<Employee> from Uni<Integer>)
                        return getEmployeeById(2);
                    } catch (InterruptedException e) {
                        return Uni.createFrom().nullItem();
                    }
                }).subscribe().with(item -> {
                    if(item.isPresent()) {
                        System.out.println(item);
                    } else {
                        System.out.println("No Item");
                    }
                });
    }

    private static Uni<Optional<Employee>> getEmployeeById(Integer id) throws InterruptedException {
        Thread.sleep(100);
        return Uni.createFrom()
                .item(employeeList.stream()
                        .filter(e-> Objects.equals(e.getId(), id))
                        .findAny());
    }

}
