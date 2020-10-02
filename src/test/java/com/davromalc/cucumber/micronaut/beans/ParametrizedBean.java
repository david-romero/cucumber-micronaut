package com.davromalc.cucumber.micronaut.beans;

public interface ParametrizedBean<T, R> {

    R doStuff(T t);

}
