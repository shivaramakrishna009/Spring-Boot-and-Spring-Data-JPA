package com.krishnaallu009.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class SpringBootAndDataJpaApplication {

	public static void main(String[] args) {
        var app = new SpringApplication(SpringBootAndDataJpaApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "dev"));
		var ctx = app.run(args);

        MyFirstService myFirstService = ctx.getBean(MyFirstService.class);
        System.out.println(myFirstService.tellAStory());
        System.out.println(myFirstService.getMyProperty());
        System.out.println(myFirstService.getMyPropertyFromCustomPropertyFile());
        System.out.println(myFirstService.getMyPropertyFromCustomProperty2File());
	}



}
