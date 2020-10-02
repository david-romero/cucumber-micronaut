package com.davromalc.cucumber.micronaut.beans;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

public class ListToStringBasedParametrizesBean implements ParametrizedBean<List<?>, String> {

    @Override
    public String doStuff(List<?> objects) {
        return String.join(",", objects.stream().map(Object::toString).collect(toUnmodifiableList()));
    }
}
