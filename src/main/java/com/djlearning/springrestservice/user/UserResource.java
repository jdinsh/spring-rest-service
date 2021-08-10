package com.djlearning.springrestservice.user;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

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
        Optional<User> user = userDaoService.findOne(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException(id + " not found");
        }else{
            return user.get();
        }

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
