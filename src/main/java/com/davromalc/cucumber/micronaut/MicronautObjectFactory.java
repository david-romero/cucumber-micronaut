package com.davromalc.cucumber.micronaut;

import io.cucumber.core.backend.ObjectFactory;
import io.cucumber.core.exception.CucumberException;
import io.micronaut.context.ApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
        Constructor<T> constructor = (Constructor<T>) beanType.getConstructors()[0];
        Object[] parameters = new Object[constructor.getParameterCount()];
        for (int index = 0; index < constructor.getParameters().length; index++) {
            parameters[index] = applicationContext.getBean(constructor.getParameterTypes()[index]);
        }
        try {
            return constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalStateException | InvocationTargetException
                | IllegalAccessException e) {
            throw new CucumberException(String.format("Failed to instantiate %s", beanType), e);
        }
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        return true;
    }
}
