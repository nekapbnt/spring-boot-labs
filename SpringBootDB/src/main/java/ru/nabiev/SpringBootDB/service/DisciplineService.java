package ru.nabiev.SpringBootDB.service;

import org.springframework.stereotype.Service;
import ru.nabiev.SpringBootDB.entity.Discipline;
import ru.nabiev.SpringBootDB.entity.Student;

import java.util.List;


@Service
public interface DisciplineService {
    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);
}

