package org.shashank.mutiny.examples;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.Test;

public class UniTest {
    @Test
    public void testUni() {
        Uni<String> uni = Uni.createFrom().item(() -> "Shashank")
                .onItem().transform(String::toUpperCase);

        UniAssertSubscriber<String> subscriber = uni.subscribe().withSubscriber(UniAssertSubscriber.create());
        subscriber.assertCompleted().assertItem("SHASHANK");

    }

}
