// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.repository;

import info.niteshjha.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    public Post getPostByUser_IdAndId(Integer user_id, Integer id);

    public List<Post> getAllByUser_Id(Integer user_id);

}
