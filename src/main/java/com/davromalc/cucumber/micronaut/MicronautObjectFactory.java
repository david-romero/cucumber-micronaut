package com.davromalc.cucumber.micronaut;

import io.cucumber.core.backend.ObjectFactory;
import io.cucumber.core.exception.CucumberException;
import io.micronaut.context.ApplicationContext;
import io.micronaut.inject.qualifiers.Qualifiers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

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
        final Constructor<T> constructor = (Constructor<T>) beanType.getConstructors()[0];
        final Object[] parameters = new Object[constructor.getParameterCount()];
        try {
            for (int index = 0; index < constructor.getParameters().length; index++) {
                final Type parameterizedType = constructor.getParameters()[index].getParameterizedType();
                if (hasParameterType(parameterizedType)) {
                    parameters[index] = getParameterizedBean(constructor, index, parameterizedType);
                } else {
                    parameters[index] = applicationContext.getBean(constructor.getParameterTypes()[index]);
                }
            }
            return constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalStateException | InvocationTargetException | IllegalAccessException
                | NoSuchFieldException | ClassNotFoundException e) {
            throw new CucumberException(String.format("Failed to instantiate %s", beanType), e);
        }
    }

    private <T> Object getParameterizedBean(Constructor<T> constructor, int index, Type parameterizedType)
            throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        final Field field = parameterizedType.getClass().getDeclaredField("actualTypeArguments");
        field.setAccessible(true);
        Type[] types = (Type[]) field.get(parameterizedType);
        Class<?>[] typesClass = new Class[types.length];
        for (int indexType = 0; indexType < types.length; indexType++) {
            typesClass[indexType] = Class.forName(types[indexType].getTypeName());
        }
        return applicationContext.getBean(constructor.getParameterTypes()[index], Qualifiers.byType(typesClass));
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        return true;
    }

    private boolean hasParameterType(Type type) {
        try {
            type.getClass().getDeclaredField("actualTypeArguments");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
