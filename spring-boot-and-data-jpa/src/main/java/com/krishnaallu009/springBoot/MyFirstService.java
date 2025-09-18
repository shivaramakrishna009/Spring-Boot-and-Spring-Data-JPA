package com.krishnaallu009.springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:custom.properties")
public class MyFirstService {

    private MyFirstClass myFirstClass;

    private Environment environment;

    @Value("Srk <3 B")
    private String myProperty;

    @Value("${my.prop}")
    private String myPropertyFromCustomPropertyFile;


//    Setter Injection
    @Autowired
    public void setMyFirstClass(@Qualifier("myThirdClass") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String tellAStory() {
        return "The dependency is saying : " + myFirstClass.greet() + " from MyFirstService";
    }

    public String getMyProperty() {
        return myProperty;
    }

    public String getMyPropertyFromCustomPropertyFile() {
        return myPropertyFromCustomPropertyFile;
    }

}
