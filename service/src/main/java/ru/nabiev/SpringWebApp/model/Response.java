package ru.nabiev.SpringWebApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    // Уникальный ID
    private String uid;
    // Уникальный ID операции
    private String operationUid;
    // Имя системы
    private String systemName;
    // Вермя создания запроса
    private String systemTime;
    // Код выполнения
    private Codes code;
    // Годовая премия
    private Double annualBonus;
    // Код ошибки
    private ErrorCodes errorCode;
    // Сообщение об ошибке
    private ErrorMessages errorMessage;
}
