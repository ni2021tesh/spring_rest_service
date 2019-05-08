// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.service;

import info.niteshjha.model.Post;

import java.util.List;

public interface PostService {
    public Post getPostByUserIdAndPostId(Integer userId,Integer postId);

    public List<Post> getAllPostForUserId(Integer userId);

    public void deletePostByPostId(Integer postId);

    public Post createPost(Post post);

    public Post modifyPost(Post post);
}
