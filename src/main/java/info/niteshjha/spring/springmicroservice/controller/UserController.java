// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.spring.springmicroservice.controller;

import info.niteshjha.spring.springmicroservice.exception.UserNotFoundException;
import info.niteshjha.spring.springmicroservice.model.User;
import info.niteshjha.spring.springmicroservice.service.UserService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{userId}")
    public Resource<User> getUserById(@PathVariable(value = "userId") Integer userId) {

        User userById = userService.getUserById(userId);
        if (userById == null) {
            throw new UserNotFoundException("User With id : " + userId + " does not exists");
        }
        Resource<User> userResource = new Resource<>(userById);
        ControllerLinkBuilder controllerLinkBuilder = ControllerLinkBuilder.
                        linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());


        ControllerLinkBuilder selfLinkBuilder = ControllerLinkBuilder.
                        linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUserById(userId));

        userResource.add(controllerLinkBuilder.withRel("all-users"));
        userResource.add(selfLinkBuilder.withRel("self"));
        return userResource;
    }



    @GetMapping(value = "/users")
    public ResponseEntity<Object> getAllUsers() {

        List<User> userById = userService.getAllUsers();
        if (userById == null || userById.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.
                        status(HttpStatus.OK).body(userById);
    }


    @PostMapping(value = "/users")
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        User savedUser = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable(value = "userId") Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/users")
    public ResponseEntity updateUser(@RequestBody @Valid User user) {
        User savedUser = userService.modifyUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }


}
