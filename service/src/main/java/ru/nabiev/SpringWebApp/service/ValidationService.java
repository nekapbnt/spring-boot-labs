package ru.nabiev.SpringWebApp.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.nabiev.SpringWebApp.exception.UnsupportedCodeException;
import ru.nabiev.SpringWebApp.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;
}
