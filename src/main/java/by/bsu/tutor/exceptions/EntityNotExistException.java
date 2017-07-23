package by.bsu.tutor.exceptions;

import lombok.Getter;

public class EntityNotExistException extends LogicException {

    @Getter
    private final String property;


    public EntityNotExistException(String message, String property) {
        super(message);
        this.property = property;
    }

}

