package org.shashank.mutiny.examples;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.test.AssertSubscriber;
import org.junit.jupiter.api.Test;

public class FailureTest {
    @Test
    public void testFailureStream() {
        Multi<Object> multi = Multi.createFrom().failure(() -> new Exception("Error"))
                .onFailure().invoke(e -> System.out.println(e));

        AssertSubscriber<Object> assertSubscriber = multi.subscribe().withSubscriber(AssertSubscriber.create(2));
        assertSubscriber.assertFailedWith(Exception.class, "Error");
    }
}
