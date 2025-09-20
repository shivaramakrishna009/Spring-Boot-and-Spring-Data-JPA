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

    @PostMapping("/post-order")
    public String postOrder(@RequestBody Order order){
        return "You have ordered: "+order.toString();
    }

    @PostMapping("/post-order-record")
    public String postOrderRecord(@RequestBody OrderRecord orderRecord){
        return "You have ordered: "+orderRecord.toString();
    }

    @GetMapping("/hi-to/{name}")
    public String hiTo(@PathVariable("name") String name){
        return "I Love You "+name + "😘😘😘";
    }
}
