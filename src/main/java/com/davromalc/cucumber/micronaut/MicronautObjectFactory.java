package com.davromalc.cucumber.micronaut;

import io.cucumber.core.backend.ObjectFactory;
import io.micronaut.context.ApplicationContext;

public final class MicronautObjectFactory implements ObjectFactory {

    ApplicationContext applicationContext;

    @Override
    public void start() {
        applicationContext = ApplicationContext.run("acceptance", "test");
    }

    @Override
    public void stop() {
        if (applicationContext != null) {
            applicationContext.stop();
        }
    }

    @Override
    public <T> T getInstance(Class<T> beanType) {
        return applicationContext.getBean(beanType);
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        return true;
    }
}
