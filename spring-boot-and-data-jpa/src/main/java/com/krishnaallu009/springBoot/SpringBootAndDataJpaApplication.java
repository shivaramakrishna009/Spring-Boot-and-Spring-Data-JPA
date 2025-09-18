package com.krishnaallu009.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAndDataJpaApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(SpringBootAndDataJpaApplication.class, args);

        MyFirstService myFirstService = ctx.getBean(MyFirstService.class);
        System.out.println(myFirstService.tellAStory());
        System.out.println(myFirstService.getJavaVersion());
        System.out.println(myFirstService.getOsName());
	}



}
