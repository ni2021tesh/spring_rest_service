// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.repository;

import info.niteshjha.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
