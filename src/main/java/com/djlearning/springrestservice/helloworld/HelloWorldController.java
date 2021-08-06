package com.djlearning.springrestservice.helloworld;

import org.springframework.web.bind.annotation.*;

//controller
@RestController
public class HelloWorldController {

    /**
     * Notes:
     * We covered
     * * Spring auto configuration -
     *      * Dispatcher servlet
     *      * Message converter with Jackson converter
     *      * Mapping - get post delete
     *      * /error page
     *      * Flow -> /get - helloworld to dispoatcher servlet -> dispatcher servlet identifies the path ->
     *      gets the spring bean and method to executes -> executes -> check if the bean is the response ->
     *      uses jackson to convert the bean to json we can use responsebody to let dispatcherservlet to to
     *      to which format we can convert
     */

    @GetMapping(path ="/hello-world")
    public String helloWorld(){
        return "HELLO WORLD";
    }

    @GetMapping(path ="/hello-world-bean")
    public HellowWorldBean helloWorldBean(){
        return new HellowWorldBean("HELLO WORLD");
    }

    @GetMapping(path ="/hello-world-bean/pathvariable/{name}")
    public HellowWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HellowWorldBean(String.format( "HELLO WORLD, %s", name));
    }
}
