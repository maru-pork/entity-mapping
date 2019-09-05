package com.example.controller;

import com.example.entitymapping.EntityMappingApplication;
import com.example.entitymapping.controller.HomeController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntityMappingApplication.class)
public class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(homeController).isNotNull();
    }
}
