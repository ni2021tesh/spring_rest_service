package info.niteshjha;

import info.niteshjha.model.User;
import info.niteshjha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringMicroserviceApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

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

        this.userService.createUser(user);
        this.userService.createUser(user1);
        this.userService.createUser(user2);
        this.userService.createUser(user3);
        this.userService.createUser(user4);
    }
}
