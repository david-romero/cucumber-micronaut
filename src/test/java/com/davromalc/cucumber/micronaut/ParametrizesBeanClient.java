package com.davromalc.cucumber.micronaut;

public class ParametrizesBeanClient {

  final ParametrizedBean<Long, String> myBean;

  public ParametrizesBeanClient(ParametrizedBean<Long, String> myBean) {
    this.myBean = myBean;
  }

  public void main() {
    System.out.println(myBean.doStuff(1L));
  }
}
