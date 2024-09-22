package ru.eremin.vacationpaycalculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface CalculatorService {
    String calculateVacation(int averageSalary, int numberVacationDays);
    String calculateVacationWithTheStartDate(int averageSalary, int numberVacationDays, LocalDate startDay);
}
