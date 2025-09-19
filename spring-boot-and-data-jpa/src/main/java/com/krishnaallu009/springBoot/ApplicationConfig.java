package com.krishnaallu009.springBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfig {

    @Bean
    @Profile("dev")
    public MyFirstClass myFirstClass() {
        return new MyFirstClass("First Bean");
    }

    @Bean
    @Profile("test")
    public MyFirstClass mySecondClass() {
        return new MyFirstClass("Second Bean");
    }

    @Bean
//    @Primary
    public MyFirstClass myThirdClass() {
        return new MyFirstClass("Third Bean");
    }
}
