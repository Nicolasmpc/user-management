package com.nicolasmpc.usermanagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicolasmpc.usermanagement.Model.User;
import com.nicolasmpc.usermanagement.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }
 
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public User updateByID(long id, User userUpdated){
        User user = userRepository.findById(id).get();
        user.setName(userUpdated.getName());
        user.setBirthDate(userUpdated.getBirthDate());

        return userRepository.save(user);
    }

    public User findByID(long id){
        return userRepository.findById(id).get();
    }

}
