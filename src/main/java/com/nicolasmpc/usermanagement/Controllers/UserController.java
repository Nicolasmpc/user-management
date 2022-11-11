package com.nicolasmpc.usermanagement.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasmpc.usermanagement.Model.User;
import com.nicolasmpc.usermanagement.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.created(null).body(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> list(){
        return ResponseEntity.ok().body(userService.listUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User user){
        User userSavedOnDataBase = userService.updateByID(id, user);
        return ResponseEntity.ok().body(userSavedOnDataBase);   
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findByID(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.findByID(id));
    }

}
