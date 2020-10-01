package com.davromalc.cucumber.micronaut;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;

import javax.inject.Singleton;

@Requires(env = "acceptance")
@Singleton
@Primary
public class MyBean {

    public void doStuff() {

    }

}
