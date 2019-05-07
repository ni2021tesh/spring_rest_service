// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.spring.springmicroservice.exception;

public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String message) {
        super(message);
    }
}
