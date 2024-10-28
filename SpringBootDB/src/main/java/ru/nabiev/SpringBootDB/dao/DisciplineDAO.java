package ru.nabiev.SpringBootDB.dao;

import org.springframework.stereotype.Repository;
import ru.nabiev.SpringBootDB.entity.Discipline;

import java.util.List;

@Repository
public interface DisciplineDAO {
    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);
}
