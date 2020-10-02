package com.davromalc.cucumber.micronaut;

public interface ParametrizedBean<T, R> {

    R doStuff(T t);

}
