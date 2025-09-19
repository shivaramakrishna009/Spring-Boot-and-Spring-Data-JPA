package com.krishnaallu009.springBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test") // This configuration will be active for 'test' profile
@Configuration
public class ApplicationConfig {

    @Bean
    public MyFirstClass myFirstClass() {
        System.out.println("First Bean Created");
        return new MyFirstClass("First Bean");
    }

    @Bean
    public MyFirstClass mySecondClass() {
        System.out.println("Second Bean Created");
        return new MyFirstClass("Second Bean");
    }

    @Bean
    public MyFirstClass myThirdClass() {
        System.out.println("Third Bean Created");
        return new MyFirstClass("Third Bean");
    }
}
