package com.safetynet.safetynetalerts.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UrlsControllerTest {

    @Autowired
    private UrlsController UrlsController;

    @Test
    public void contextLoads() {
        assertThat(UrlsController).isNotNull();
    }
}