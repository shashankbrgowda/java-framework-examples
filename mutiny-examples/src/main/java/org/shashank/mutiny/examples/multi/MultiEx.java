package org.shashank.mutiny.examples.multi;

import io.smallrye.mutiny.Multi;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/*
 * A Multi represents a stream of data. A stream can emit 0, 1, n, or an infinite number of items.
 * You will rarely create instances of Multi yourself but instead use a reactive client that exposes a Mutiny API. Still,
 * just like Uni there exists a rich API for creating Multi objects.
 *
 *
 * A Multi<T> is a data stream that:
 * - emits 0..n item events
 * - emits a failure event
 * - emits a completion event for bounded streams
 *
 * Failures are terminal events: after having received a failure no further item will be emitted.
 */
public class MultiEx {
    public static void main(String[] args) {
        Multi.createFrom().items("Shashank", "abc", "xyz")
                .onItem().transform(String::toUpperCase)
                .select().first(2) // self explanatory
                .onFailure().recoverWithItem("Gowda")
                .subscribe().with(System.out::println); // prints all the items

        //Subscribing to a multi
        // 1. item 2. failure and 3. completion event
        Multi.createFrom().items("Shashank", "abc", "xyz")
                .onItem().transform(String::toLowerCase)
                .subscribe().with(i -> System.out.println(i), f -> System.out.println(f),
                        () -> System.out.println("Completed - do something here after completion"));

        // Multi from iterables
        Multi.createFrom().iterable(Arrays.asList("A", "B", "C"))
                .subscribe().with(System.out::print);

        System.out.println();
        // items from supplier
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Multi.createFrom().items(() -> IntStream.range(atomicInteger.getAndIncrement(), atomicInteger.get()+4).boxed())
                .subscribe().with(System.out::print);

        System.out.println();
        // Multi from range
        Multi.createFrom().range(1, 6).subscribe().with(System.out::print);

        System.out.println();
        // Failing multis
        Multi<Integer> multis = Multi.createFrom().failure(() -> new Exception("Kaboo000m"));
        multis.subscribe().with(System.out::println);

        // creating empty multis
        // Unlike Uni, Multi streams don’t send null items (this is forbidden in reactive streams).
        // Instead Multi streams send completion events indicating that there are no more items to consume.
        // Of course, the completion event can happen even if there are no items, creating an empty stream.
        Multi<String> multi = Multi.createFrom().empty();

        // Emitter
        /*
        You can create a Multi using an emitter. This approach is useful when integrating callback-based APIs:
        The emitter can also send a failure. It can also get notified of cancellation to, for example, stop the work in progress.
         */
        Multi<String> emitterMulti = Multi.createFrom().emitter(em -> {
            em.emit("Emitter - A");
            em.emit("Emitter - B");
            em.emit("Emitter - C");
            em.complete();
        });
        emitterMulti.subscribe().with(System.out::println);

        // Creating Multis from ticks
        /*
        You can create a stream that emit a ticks periodically:
        The downstream receives a long, which is a counter. For the first tick, it’s 0, then 1, then 2, and so on.
         */
        // emit tick every 100 milliseconds
        /*Multi.createFrom().ticks()
                .every(Duration.ofMillis(100))
                .select().first(20)
                .subscribe().with(System.out::println);*/
        //NOTE: even if we have selected first 20 and prints only 20 ticks, looks like events are still being emitted
        // and application is running forever

        // Creating Multis from a generator
        // You can create a stream from some initial state, and a generator function:
        Multi<Integer> multiGenerators = Multi.createFrom().generator(() -> 1, (n, em) -> {
            int next = n + (n/2) + 1;

            if(n < 50) {
                em.emit(next);
            } else {
                em.complete();
            }
            return next;
        });
        multiGenerators.subscribe().with(System.out::print);
    }
}
