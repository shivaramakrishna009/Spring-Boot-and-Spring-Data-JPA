package com.krishnaallu009.springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {

//    Field Injection
//    @Autowired
//    @Qualifier("mySecondClass")
    private MyFirstClass myFirstClass;

//    Constructor Injection
//    @Autowired
//    public MyFirstService(MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }

//    Setter Injection
    @Autowired
    public void setMyFirstClass(@Qualifier("myThirdClass") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellAStory() {
        return "The dependency is saying : " + myFirstClass.greet() + " from MyFirstService";
    }
}
