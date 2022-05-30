package org.shashank.mutiny.examples.uni;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.Cancellable;

import java.util.concurrent.atomic.AtomicInteger;

/* mostly we'll not create any uni's or multi's and we receive those events from smallrye mutiny library either from db
 or from a rest call.. but its important to know how it works.*/
public class CreatingUnis {
    public static void main(String[] args) {
        // from a know value
        Uni.createFrom().item(1)
                .onItem().transform(integer -> integer+1)
                .subscribe().with(System.out::println);

        // sending 2 callbacks for subscribe - one for item and another for failure
        Uni<String> uni = Uni.createFrom().item("Shashank")
                .onItem().transform(String::toUpperCase);
        uni.subscribe().with(item -> System.out.println(item + "1"), failure -> System.out.println());

        // subscribing uni multiple times
        uni.subscribe().with(item -> System.out.println(item + "2"), failure -> System.out.println());


        // using supplier and supplier returns different value for each subscription <--- IMPORTANT
        AtomicInteger aI = new AtomicInteger(1);
        Uni<Integer> uni2 = Uni.createFrom().item(() -> aI.getAndIncrement());
        uni2.subscribe().with(i -> System.out.println("atomic integer supplier multiple subscription - " + i));
        uni2.subscribe().with(i -> System.out.println("atomic integer supplier multiple subscription - " + i));

        // subscribe with returns cancellable so that we can cancel the operation if needed
        Cancellable cancellable = Uni.createFrom().item("Hello ")
                .onItem().transform(item -> item + "World")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(System.out::println);
    }
}
