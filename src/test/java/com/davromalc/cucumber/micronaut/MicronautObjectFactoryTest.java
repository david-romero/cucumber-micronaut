package com.davromalc.cucumber.micronaut;

import io.cucumber.core.backend.ObjectFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MicronautObjectFactoryTest {

    MicronautObjectFactory objectFactory = new MicronautObjectFactory();

    @BeforeEach
    void setUp() {
        objectFactory.start();
    }

    @AfterEach
    void tearDown() {
        objectFactory.stop();
    }

    @Test
    void given_A_Bean_Registered_Into_Micronaut_Context_When_Cucumber_Gets_An_Instance_Then_The_Bean_Is_Returned() {
        // given
        objectFactory.applicationContext.registerSingleton(MyBean.class);

        // when
        final MyBean myBean = objectFactory.getInstance(MyBean.class);

        // then
        Assertions.assertNotNull(myBean);
    }
}
