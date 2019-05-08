// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.controller;

import info.niteshjha.exception.UserNotFoundException;
import info.niteshjha.model.Post;
import info.niteshjha.model.User;
import info.niteshjha.service.PostService;
import info.niteshjha.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"posts resource"})
@RequestMapping(value = "/users/{userId}")
public class PostController {

    private PostService postService;

    private UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @ApiOperation(value = "search a post of user with an postID", response = Post.class, notes = "This endpoint is used to retrieve users post based on postId.")
    @GetMapping(value = "/posts/{postId}")
    public Post getPostByPostId(@PathVariable Integer postId, @PathVariable Integer userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id :" + userId + " not found");
        }
        return postService.getPostByUserIdAndPostId(user.getId(), postId);
    }


    @ApiOperation(value = "view a list of available post for given users", response = List.class, notes = "This endpoint is used to retrieve all the saved post for the given user from database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved post list for given user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping(value = "/posts")
    public List<Post> getAllPostForUserId(@PathVariable Integer userId) {

        User userById = userService.getUserById(userId);
        if (userById == null) {
            throw new UserNotFoundException("User with id :" + userId + " not found");
        }
        return postService.getAllPostForUserId(userById.getId());
    }

}
