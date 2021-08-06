package com.djlearning.springrestservice.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static Integer usercount = 3;
    static {
        users.add(new User(1,"Dinesh",new Date()));
        users.add(new User(2,"Divya",new Date()));
        users.add(new User(3,"Dhiyan",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(usercount++);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
      return users.stream().filter(user ->  user.getId()==id).findFirst().get();
    }
}
