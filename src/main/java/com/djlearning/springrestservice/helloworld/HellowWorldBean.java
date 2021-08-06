package com.djlearning.springrestservice.helloworld;

public class HellowWorldBean {

    private String message;
    public HellowWorldBean(String message){
        this.message = message;
    }

    public String getMessage(){
        return  this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return "HellowWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
