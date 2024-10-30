package ru.nabiev.SpringBootDB.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabiev.SpringBootDB.entity.Discipline;
import ru.nabiev.SpringBootDB.service.DisciplineService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/disciplines")
    public ResponseEntity<List<Discipline>> allDisciplines() {
        try {
            List<Discipline> allDisciplines = disciplineService.getAllDisciplines();
            if (allDisciplines.isEmpty()) {
                log.info("Not found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("Success: {}", allDisciplines);
            return new ResponseEntity<>(allDisciplines, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<Discipline> getDiscipline(@PathVariable("id") int id) {
        try {
            Discipline discipline = disciplineService.getDiscipline(id);
            if (discipline == null) {
                log.info("{} not found.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            log.info("Success id {}: {}", id, discipline);
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/disciplines")
    public ResponseEntity<Discipline> saveDiscipline(@RequestBody Discipline discipline) {
        try {
            Discipline savedDiscipline = disciplineService.saveDiscipline(discipline);
            if (savedDiscipline == null) {
                log.error("Not found");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            log.info("Success id {}", savedDiscipline.getId());
            return new ResponseEntity<>(savedDiscipline, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/disciplines")
    public ResponseEntity<Discipline> updateDiscipline(@RequestBody Discipline discipline) {
        try {
            Discipline updatedDiscipline = disciplineService.saveDiscipline(discipline);
            if (updatedDiscipline == null) {
                log.error("Not found");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            log.info("Success id {}", updatedDiscipline.getId());
            return new ResponseEntity<>(updatedDiscipline, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable("id") int id) {
        try {
            disciplineService.deleteDiscipline(id);
            log.info("Success id {} .", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}