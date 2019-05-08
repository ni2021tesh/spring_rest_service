// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.controller;

import info.niteshjha.exception.UserNotFoundException;
import info.niteshjha.model.User;
import info.niteshjha.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = {"users resource"})
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "search a user with an ID", response = User.class, notes = "This endpoint is used to retrieve user based on userId.")
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



    @ApiOperation(value = "view a list of available users", response = List.class, notes = "This endpoint is used to retrieve all the saved user "
                    + "list from the database.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved user list"),
                    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping(value = "/users")
    public ResponseEntity<Object> getAllUsers() {

        List<User> userById = userService.getAllUsers();
        if (userById == null || userById.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.
                        status(HttpStatus.OK).body(userById);
    }


    @ApiOperation(value = "add a user", response = User.class, notes = "This endpoint is used to create a new user.")
    @PostMapping(value = "/users")
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        User savedUser = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @ApiOperation(value = "delete a user", response = Void.class, notes = "This endpoint is used to delete the user from the database based on "
                    + "userId.")
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable(value = "userId") Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "update a user", response = User.class, notes = "This endpoint is used to update the saved user by providing the userId.")
    @PutMapping(value = "/users/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @RequestBody @Valid User user) {
        User retrievedUser = userService.getUserById(userId);

        if (retrievedUser == null) {
            throw new UserNotFoundException("User With Id :" + userId + " not found");
        }

        User savedUser = userService.modifyUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }
}
