package ru.nabiev.SpringWebApp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.nabiev.SpringWebApp.model.Request;

@Service
@Qualifier("SendModifiedRequestService")
public class SendModifiedRequestService implements SendRequestService {
    public void send(Request request) {
        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Request>() {});
    }
}
