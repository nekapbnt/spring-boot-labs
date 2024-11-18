package ru.nabiev.SpringBootWebSecurity.service;

import ru.nabiev.SpringBootWebSecurity.entity.Part;

import java.util.List;

public interface PartService {
    List<Part> filterParts(String article, String model, String brand);
}