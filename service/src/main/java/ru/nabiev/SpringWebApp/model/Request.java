package ru.nabiev.SpringWebApp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    // Уникальный ID
    @NotBlank
    @Size(max=32)
    private String uid;

    // Уникальный ID операции
    @NotBlank
    @Size(max=32)
    private String operationUid;
    // Имя системы
    private String systemName;

    // Время создания запроса
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String systemTime;
    // Источник запроса
    private String source;
    // Должность
    private Positions position;
    // Зарплата
    private Double salary;
    // Премия
    private Double bonus;
    // Количество рабочих дней
    private Integer workDays;

    // Уникальный ID связи
    @Min(1)
    @Max(100000)
    private Integer communicationId;
    // ID шаблона
    private Integer templateId;
    // Код продукта
    private Integer productCode;
    // SMS код
    private Integer smsCode;

    @Override
    public String toString() {
        return "Request{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}
