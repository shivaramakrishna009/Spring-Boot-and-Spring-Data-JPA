package com.krishnaallu009.springBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Bean("bean1")
    public MyFirstClass myFirstClass() {
        return new MyFirstClass("First Bean");
    }

    @Bean("bean2")
    public MyFirstClass mySecondClass() {
        return new MyFirstClass("Second Bean");
    }

}
