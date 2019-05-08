package info.niteshjha.service;

import info.niteshjha.exception.PostNotFoundException;
import info.niteshjha.model.Post;
import info.niteshjha.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post getPostByUserIdAndPostId(Integer userId, Integer postId) {
        Post postByUser_idAndId = postRepository.getPostByUser_IdAndId(userId, postId);

        if (postByUser_idAndId == null)
            throw new PostNotFoundException("Post with id :" + postId + " not found");
        return postByUser_idAndId;
    }

    @Override
    public List<Post> getAllPostForUserId(Integer userId) {
        List<Post> allByUser_id = postRepository.getAllByUser_Id(userId);

        if (allByUser_id.size() <= 0)
            throw new PostNotFoundException("Post does not exists for userId :" + userId);

        return allByUser_id;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deletePostByPostId(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Post modifyPost(Post post) {
        return postRepository.save(post);
    }
}
