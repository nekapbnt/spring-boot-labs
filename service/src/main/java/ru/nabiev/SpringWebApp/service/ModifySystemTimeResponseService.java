package ru.nabiev.SpringWebApp.service;

import org.springframework.stereotype.Service;
import ru.nabiev.SpringWebApp.model.Response;
import ru.nabiev.SpringWebApp.util.DateTimeUtil;

import java.util.Date;

@Service
public class ModifySystemTimeResponseService implements ModifyResponseService{
    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        return response;
    }
}
