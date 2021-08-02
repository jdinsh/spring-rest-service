package com.djlearning.springrestservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//controller
@RestController
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path ="/hello-world")
    public String helloWorld(){
        return "HELLO WORLD";
    }
}
