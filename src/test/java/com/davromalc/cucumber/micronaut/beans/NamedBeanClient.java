package com.davromalc.cucumber.micronaut.beans;

import javax.inject.Named;

import java.util.List;

public class NamedBeanClient {

    public final ParametrizedBean<List<String>, String> myBean;

    public NamedBeanClient(@Named("namedParametrizedBean") ParametrizedBean<List<String>, String> myBean) {
        this.myBean = myBean;
    }

    public void main() {
        System.out.println(myBean.doStuff(List.of("Testing")));
    }
}
