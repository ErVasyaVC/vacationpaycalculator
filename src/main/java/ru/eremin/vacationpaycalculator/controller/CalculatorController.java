package ru.eremin.vacationpaycalculator.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eremin.vacationpaycalculator.service.CalculatorService;

import java.time.LocalDate;


@RestController
@RequestMapping
@AllArgsConstructor
public class CalculatorController {

    private CalculatorService calculatorService;

    @GetMapping("/calculate")
    public String calculateVacation(@RequestParam(value = "averageSalary", required = true) int averageSalary,
                                    @RequestParam(value = "numberVacationDays", required = true) int numberVacationDays,
                                    @RequestParam(value = "startDay", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDay) {

        String vacationPayment;

        if (startDay == null){
            vacationPayment = calculatorService.calculateVacation(averageSalary, numberVacationDays);
        } else {
            vacationPayment = calculatorService.calculateVacationWithTheStartDate(averageSalary, numberVacationDays,
                    startDay);
        }

        return vacationPayment;
    }
}
