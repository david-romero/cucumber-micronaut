package com.davromalc.cucumber.micronaut.beans;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

public class StringListToStringBasedParametrizesBean implements ParametrizedBean<List<String>, String> {

    @Override
    public String doStuff(List<String> objects) {
        return String.join(",", objects.stream().map(Object::toString).collect(toUnmodifiableList()));
    }
}
