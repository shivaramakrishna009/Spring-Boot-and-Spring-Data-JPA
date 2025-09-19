package com.krishnaallu009.springBoot;

import org.springframework.stereotype.Component;

//@Component  // Spring no longer auto-registers it during component scanning.
public class MyFirstClass {

    private String message;

    public MyFirstClass(String message) {
        this.message = message;
    }

    public MyFirstClass() {
    }

    public String greet() {
        return "Hello from MyFirstClass ==> message = " + message;
    }
}
