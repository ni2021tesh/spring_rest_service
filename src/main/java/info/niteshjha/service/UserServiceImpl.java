// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.service;

import info.niteshjha.exception.UserNotFoundException;
import info.niteshjha.model.User;
import info.niteshjha.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> {
            return new UserNotFoundException("User With id : " + id + " does not exists");
        });
    }

    @Override
    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteUserById(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException("User With id : " + id + " does not exists");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public User modifyUser(User user) {
        return userRepository.save(user);
    }
}
