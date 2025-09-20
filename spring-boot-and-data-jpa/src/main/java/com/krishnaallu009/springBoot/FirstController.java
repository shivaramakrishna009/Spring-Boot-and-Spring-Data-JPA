package com.krishnaallu009.springBoot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello from FirstController";
    }

    @GetMapping("/hello-2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello2(){
        return "Hello from FirstController";
    }

    @PostMapping("/post")
    public String post(@RequestBody String message){
        return "You have posted: "+message;
    }
}
