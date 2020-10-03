package com.davromalc.cucumber.micronaut.beans;

import java.util.List;

public class ParametrizesStringListBeanClient {

    public final ParametrizedBean<List<String>, String> myBean;

    public ParametrizesStringListBeanClient(ParametrizedBean<List<String>, String> myBean) {
        this.myBean = myBean;
    }

    public void main() {
        System.out.println(myBean.doStuff(List.of("Testing")));
    }
}
