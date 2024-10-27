package ru.nabiev.SpringWebApp.service;

import ru.nabiev.SpringWebApp.model.Positions;
import java.time.LocalDate;
import java.time.Month;

public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workdays) {
        int daysInYear = LocalDate.now().lengthOfYear();
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workdays;
    }

    public double calculateQuarterBonus(Positions position, double salary, double bonus, int workdays, int quarter, int year) {
        if (position.isManager()) {
            return salary * bonus * position.getPositionCoefficient() * getDaysInQuarter(year, quarter) / workdays;
        } else
            return 0.0;
    }

    public static int getDaysInQuarter(int year, int quarter) {
        Month startMonth;
        Month endMonth = switch (quarter) {
            case 1 -> {
                startMonth = Month.JANUARY;
                yield Month.MARCH;
            }
            case 2 -> {
                startMonth = Month.APRIL;
                yield Month.JUNE;
            }
            case 3 -> {
                startMonth = Month.JULY;
                yield Month.SEPTEMBER;
            }
            case 4 -> {
                startMonth = Month.OCTOBER;
                yield Month.DECEMBER;
            }
            default -> throw new IllegalArgumentException("Invalid quarter");
        };

        LocalDate startDate = LocalDate.of(year, startMonth, 1);
        LocalDate endDate = LocalDate.of(year, endMonth, endMonth.length(startDate.isLeapYear()));

        return endDate.getDayOfYear() - startDate.getDayOfYear() + 1;
    }
}
