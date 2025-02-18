package com.api.crud.controllers;

import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;
import com.fasterxml.jackson.core.JsonEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import javax.swing.plaf.OptionPaneUI;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:4200")
public class userController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public ArrayList<UserModel> getUser() {
        return this.userService.getUsers();
    }

    @PostMapping(path = "/user")
    public Map saveUser(@RequestBody UserModel user) {
        this.userService.saveUser(user);
        return Map.of("mensaje", "Usuario Creado correctamente!");

    }

    @GetMapping(path = "user/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getByID(id);
    }

    @PutMapping(path = "user/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id) {
        Optional<UserModel> userModel= userService.getByID(id);

      return this.userService.updateById(request,id);

    }

    @DeleteMapping(path = "user/{id}")
    public Map<String ,String> deleteById(@PathVariable("id") Long id) {

        boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return Map.of("mensaje", "\"Usuario con el ID \" + id + \" fue eliminado\";");

        } else {
            return Map.of("error","Error");
        }

    }
}


