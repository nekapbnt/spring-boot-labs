package ru.nabiev.SpringWebApp.service;

import org.springframework.stereotype.Service;
import ru.nabiev.SpringWebApp.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
