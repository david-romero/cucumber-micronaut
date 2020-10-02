package com.davromalc.cucumber.micronaut.beans;

import java.util.List;

public class ParametrizesListBeanClient {

    public final ParametrizedBean<List<?>, String> myBean;

    public ParametrizesListBeanClient(ParametrizedBean<List<?>, String> myBean) {
        this.myBean = myBean;
    }

    public void main() {
        System.out.println(myBean.doStuff(List.of(1L)));
    }
}
