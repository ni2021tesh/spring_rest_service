// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.service;

import info.niteshjha.model.User;

import java.util.List;

public interface UserService {
    public User getUserById(Integer id);

    public List<User> getAllUsers();

    public void deleteUserById(Integer id);

    public User createUser(User user);

    public User modifyUser(User user);
}
