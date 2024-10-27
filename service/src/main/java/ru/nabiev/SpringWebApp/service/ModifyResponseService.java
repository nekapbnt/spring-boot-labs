package ru.nabiev.SpringWebApp.service;

import org.springframework.stereotype.Service;
import ru.nabiev.SpringWebApp.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
