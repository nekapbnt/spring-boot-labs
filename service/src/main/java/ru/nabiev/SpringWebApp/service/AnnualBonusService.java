package ru.nabiev.SpringWebApp.service;

import ru.nabiev.SpringWebApp.model.Positions;

public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workdays);
}
