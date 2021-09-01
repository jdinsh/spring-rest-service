package com.djlearning.springrestservice.user;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public EntityModel<User> getUserById(@PathVariable int id){
        Optional<User> user = userDaoService.findOne(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException(id + " not found");
        }else{
            /**
             * Use Entity Model from spring-boot-starter-hateoas
             * to create the link to all users
             * which will append as link all-users in the response we  are sending.
             * WebMvcLinkBuilder - linkTo (To what invocationValue & methodOn
             * (Method  to add  from controller class)
             */
            EntityModel<User> userEntityModel  = EntityModel.of(user.get());
            WebMvcLinkBuilder linkToUsers =
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
            userEntityModel.add(linkToUsers.withRel("all-users"));
            return userEntityModel;
        }

    }

    /**
     * Import
     * spring-boot-starter-validation  to add the  validation
     * To validate the incoming request  -
     * Use @Valid parameter
     * Add Validation annotation like size past email etc  inside the User entity to trigger the validation
     * Also add the message
     * @param user
     * @return
     */
    @PostMapping("/users")
    public  ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        //ServletUriComponentsBuilder
        User savedUser =  userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }



}
