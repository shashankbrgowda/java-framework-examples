package org.shashank.mutiny.examples;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.test.AssertSubscriber;
import org.junit.jupiter.api.Test;

public class MultiTest {
    @Test
    public void testMulti() {
        Multi<String> multi = Multi.createFrom().items("A", "B", "C")
                .onItem().transform(e -> String.format("%s-%s", "Shashank", e));

        AssertSubscriber<String> assertSubscriber = multi.subscribe().withSubscriber(AssertSubscriber.create(3));

        assertSubscriber.assertCompleted().assertItems("Shashank-A", "Shashank-B", "Shashank-C");
    }
}
