package org.shashank.mutiny.examples.failures;

import io.smallrye.mutiny.Uni;

public class TransformFailureEvent {
    public static void main(String[] args) {
        Uni.createFrom().failure(() -> new Exception("Exception"))
                .onFailure().invoke(e -> System.out.println("Error"))
                .onFailure().transform(f -> new ServiceUnavailableException("Service unavailable!"))
                .subscribe().with(System.out::println);
    }
}

class ServiceUnavailableException extends Exception {
    public ServiceUnavailableException() {
        super();
    }

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceUnavailableException(Throwable cause) {
        super(cause);
    }

    protected ServiceUnavailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
