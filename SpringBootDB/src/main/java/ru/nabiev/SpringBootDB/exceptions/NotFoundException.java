package ru.nabiev.SpringBootDB.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }
}
