package com.davromalc.cucumber.micronaut.beans;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

public class LongListToStringBasedParametrizesBean implements ParametrizedBean<List<Long>, String> {

    @Override
    public String doStuff(List<Long> objects) {
        return String.join(",", objects.stream().map(Object::toString).collect(toUnmodifiableList()));
    }
}
