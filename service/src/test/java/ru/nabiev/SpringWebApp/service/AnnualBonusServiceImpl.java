package ru.nabiev.SpringWebApp.service;

import org.junit.jupiter.api.Test;
import ru.nabiev.SpringWebApp.model.Positions;


import static org.junit.jupiter.api.Assertions.*;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {

        Positions position = Positions.HR;
        double bonus = 2.0;
        int workdays = 243;
        double salary = 100000.00;

        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workdays);

        double expected = 361481.48148148146;
        assertEquals(expected, result, 0.1);
    }

    @Test
    void calculateQuarterBonusPM() {
        Positions position = Positions.PM;
        double bonus = 2.0;
        int workdays = 243;
        int quarter = 2;
        int year = 2024;
        double salary = 100000.00;
        double result = new AnnualBonusServiceImpl().calculateQuarterBonus(position, salary, bonus, workdays, quarter, year);

        double expected = 179753.086419;
        assertEquals(expected, result, 0.1);
    }
    @Test
    void calculateQuarterBonusDEV() {
        Positions position = Positions.DEV;
        double bonus = 1.0;
        int workdays = 143;
        int quarter = 3;
        int year = 2023;
        double salary = 10000.00;
        double result = new AnnualBonusServiceImpl().calculateQuarterBonus(position, salary, bonus, workdays, quarter, year);

        double expected = 0.0;
        assertEquals(expected, result);
    }
}