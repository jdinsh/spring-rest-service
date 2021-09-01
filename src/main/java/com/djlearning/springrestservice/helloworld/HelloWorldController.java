package com.djlearning.springrestservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

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


    @Autowired
    private MessageSource messageSource;

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

    /**
     * Use Locale to get the Accept-Language request header to understand the locale
     * Use MessageSource resource bundle from spring to get the resource bundle of specfic langule
     * return back the message bundle
     *
     * @param locale
     * @return
     */
    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(
            //@RequestHeader(name="Accept-Language", required = false)Locale locale
            //Everytime dont need to add locale as parameter.
            ){
        return messageSource.getMessage("goodmorning.message",null,"Default Message",
                //locale
                LocaleContextHolder.getLocale()  //use this  instead  of passing  the  locale everytime
        );
    }

}
