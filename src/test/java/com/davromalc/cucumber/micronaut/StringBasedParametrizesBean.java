package com.davromalc.cucumber.micronaut;

public class StringBasedParametrizesBean implements ParametrizedBean<String, String> {

    @Override
    public String doStuff(String input) {
        return input.toUpperCase();
    }
}
