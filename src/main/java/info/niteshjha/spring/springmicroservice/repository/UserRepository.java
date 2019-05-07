// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.spring.springmicroservice.repository;

import info.niteshjha.spring.springmicroservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
