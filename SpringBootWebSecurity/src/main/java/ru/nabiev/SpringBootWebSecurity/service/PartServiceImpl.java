package ru.nabiev.SpringBootWebSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.nabiev.SpringBootWebSecurity.entity.Part;
import ru.nabiev.SpringBootWebSecurity.repository.PartRepository;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepository partRepository;

    @Override
    public List<Part> filterParts(String article, String model, String brand) {
        String articleFilter = StringUtils.hasText(article) ? article : null;
        String modelFilter = StringUtils.hasText(model) ? model : null;
        String brandFilter = StringUtils.hasText(brand) ? brand : null;
        return partRepository.findAll().stream()
                .filter(part -> (articleFilter == null || part.getArticle().contains(articleFilter)) &&
                        (modelFilter == null || part.getModel().contains(modelFilter)) &&
                        (brandFilter == null || part.getBrand().contains(brandFilter)))
                .toList();
    }
}