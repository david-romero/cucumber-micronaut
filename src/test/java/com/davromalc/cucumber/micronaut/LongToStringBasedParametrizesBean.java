package com.davromalc.cucumber.micronaut;

public class LongToStringBasedParametrizesBean implements ParametrizedBean<Long, String> {

  @Override
  public String doStuff(Long input) {
    return Long.toString(input);
  }
}
