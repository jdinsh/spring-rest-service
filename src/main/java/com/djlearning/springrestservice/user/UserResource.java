package com.djlearning.springrestservice.user;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private  UserDaoService  userDaoService;
    //retreiveAllUsers

    /**
     *
     * @return all users
     */
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDaoService.findAll();
    }

    /**
     *
     * @return user
     */
    @GetMapping("/users/{id}")
    public  User getUserById(@PathVariable int id){
        return userDaoService.findOne(id);
    }

    @PostMapping("/users")
    public  ResponseEntity<Object> createUser(@RequestBody User user){
        //ServletUriComponentsBuilder
        User savedUser =  userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
