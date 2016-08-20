package com.example.integration;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer {

    private AtomicInteger counter = new AtomicInteger();

    public void consumeMessage(String message) {
        System.out.println(message);
        this.counter.incrementAndGet();
    }

    public int consumedCount() {
        return this.counter.intValue();
    }

}
