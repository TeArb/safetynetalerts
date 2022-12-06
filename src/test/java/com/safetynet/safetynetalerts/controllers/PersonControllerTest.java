package com.safetynet.safetynetalerts.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonController PersonController;

    @Test
    public void contextLoads() {
        assertThat(PersonController).isNotNull();
    }
}