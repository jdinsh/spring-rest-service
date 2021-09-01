package com.djlearning.springrestservice.user;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {

    private Integer id;

    /**
     *@size Related to validation - Added as part  of spring-boot-starter-validation
     */
    @Size(min = 2,message = "Name should have at least 2 characters")
    private String name;

    /**
     *@size Related to validation - Added as part  of spring-boot-starter-validation
     */
    @Past(message = "Birthdate should be past date")
    private Date birthDate;

    protected User(){

    }

    public User(Integer id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
