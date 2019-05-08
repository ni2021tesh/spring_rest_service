package info.niteshjha;

import info.niteshjha.model.Post;
import info.niteshjha.model.User;
import info.niteshjha.service.PostService;
import info.niteshjha.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringMicroserviceApplication implements CommandLineRunner {

    private final UserService userService;

    private final PostService postService;

    public SpringMicroserviceApplication(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroserviceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("Nitesh");
        user.setBirthDate(LocalDateTime.now());

        User user1 = new User();
        user1.setName("Himanshu");
        user1.setBirthDate(LocalDateTime.now());

        User user2 = new User();
        user2.setName("Anusha");
        user2.setBirthDate(LocalDateTime.now());

        User user3 = new User();
        user3.setName("Test");
        user3.setBirthDate(LocalDateTime.now());

        User user4 = new User();
        user4.setName("Sample");
        user4.setBirthDate(LocalDateTime.now());

        User savedUser1 = this.userService.createUser(user);
        User savedUser2 = this.userService.createUser(user1);
        User savedUser3 = this.userService.createUser(user2);
        User savedUser4 = this.userService.createUser(user3);
        User savedUser5 = this.userService.createUser(user4);

        postService.createPost(new Post("Sample Post for user1", userService.getUserById(savedUser1.getId())));
        postService.createPost(new Post("Sample Post for user11", userService.getUserById(savedUser1.getId())));
        postService.createPost(new Post("Sample Post for user12", userService.getUserById(savedUser1.getId())));

        postService.createPost(new Post("Sample Post for user2", userService.getUserById(savedUser2.getId())));

        postService.createPost(new Post("Sample Post for user3", userService.getUserById(savedUser3.getId())));
        postService.createPost(new Post("Sample Post for user31", userService.getUserById(savedUser3.getId())));
        postService.createPost(new Post("Sample Post for user32", userService.getUserById(savedUser3.getId())));
    }
}
