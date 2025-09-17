package com.krishnaallu009.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAndDataJpaApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(SpringBootAndDataJpaApplication.class, args);

        MyFirstClass myFirstClass = ctx.getBean("myBean",MyFirstClass.class);
        System.out.println(myFirstClass.greet());
	}



}
