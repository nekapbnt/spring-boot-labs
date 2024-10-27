package ru.nabiev.SpringWebApp.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nabiev.SpringWebApp.exception.UnsupportedCodeException;
import ru.nabiev.SpringWebApp.exception.ValidationFailedException;
import ru.nabiev.SpringWebApp.model.*;
import ru.nabiev.SpringWebApp.service.*;
import ru.nabiev.SpringWebApp.util.DateTimeUtil;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifySystemTimeResponseService;
    private final ModifyResponseService modifyOperationUidResponseService;
    private final ModifyRequestService modifySourceRequestService;
    private final ModifyRequestService modifySystemNameRequestService;
    private final SendModifiedRequestService sendModifiedRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifySystemTimeResponseService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyOperationUidResponseService,
                        @Qualifier("ModifySystemNameRequestService") ModifySystemNameRequestService modifySystemNameRequestService,
                        @Qualifier("SendModifiedRequestService") SendModifiedRequestService sendModifiedRequestService,
                        @Qualifier("ModifySourceRequestService") ModifyRequestService modifySourceRequestService) {
        this.validationService = validationService;
        this.modifySystemTimeResponseService = modifySystemTimeResponseService;
        this.modifyOperationUidResponseService = modifyOperationUidResponseService;
        this.modifySystemNameRequestService = modifySystemNameRequestService;
        this.modifySourceRequestService = modifySourceRequestService;
        this.sendModifiedRequestService = sendModifiedRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("request source: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemName("")
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        try {
            validationService.isValid(bindingResult);
            log.info("validation success");
        } catch (ValidationFailedException e) {
            log.error(e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            log.error(e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifySystemNameRequestService.modify(request);
        modifySourceRequestService.modify(request);
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        sendModifiedRequestService.send(request);
        log.info("request : {}", request);
        response = modifySystemTimeResponseService.modify(response);
        response = modifyOperationUidResponseService.modify(response);
        log.info("response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
