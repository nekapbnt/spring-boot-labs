package ru.nabiev.SpringBootWebSecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.nabiev.SpringBootWebSecurity.entity.Part;
import ru.nabiev.SpringBootWebSecurity.repository.PartRepository;
import java.util.Optional;

@Slf4j
@Controller
public class PartController {

    @Autowired
    private PartRepository partRepository;

    @GetMapping("/list")
    public ModelAndView getAllParts() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-parts");
        mav.addObject("parts", partRepository.findAll());
        return mav;
    }

    @GetMapping("/addPartForm")
    public ModelAndView addPartForm() {
        ModelAndView mav = new ModelAndView("add-part-form");
        Part part = new Part();
        mav.addObject("part", part);
        return mav;
    }

    @PostMapping("/savePart")
    public String savePart(@ModelAttribute Part part) {
        partRepository.save(part);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long partId) {
        ModelAndView mav = new ModelAndView("add-part-form");
        Optional<Part> optionalPart = partRepository.findById(partId);
        Part part = new Part();
        if (optionalPart.isPresent()) {
            part = optionalPart.get();
        }
        mav.addObject("part", part);
        return mav;
    }

    @GetMapping("/deletePart")
    public String deletePart(@RequestParam Long partId) {
        partRepository.deleteById(partId);
        return "redirect:/list";
    }
}