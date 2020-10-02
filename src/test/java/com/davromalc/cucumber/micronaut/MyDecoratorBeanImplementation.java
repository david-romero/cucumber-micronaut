package com.davromalc.cucumber.micronaut;

public class MyDecoratorBeanImplementation implements MyBeanInterface {

  private final MyBeanInterface delegated;

  public MyDecoratorBeanImplementation(MyBeanInterface delegated) {
    this.delegated = delegated;
  }

  @Override
  public void doStuff() {
    delegated.doStuff();
  }
}
