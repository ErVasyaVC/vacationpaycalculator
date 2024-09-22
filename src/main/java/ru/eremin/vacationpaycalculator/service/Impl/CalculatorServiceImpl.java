package ru.eremin.vacationpaycalculator.service.Impl;

import org.springframework.stereotype.Service;
import ru.eremin.vacationpaycalculator.service.CalculatorService;
import ru.eremin.vacationpaycalculator.service.Holidays;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    public String calculateVacation(int averageSalary, int numberVacationDays) {
        double vacationPayment = (double) averageSalary /12 / 29.3 * numberVacationDays;

        return String.valueOf(vacationPayment);

    }

    public String calculateVacationWithTheStartDate(int averageSalary, int numberVacationDays, LocalDate startDay) {
        numberVacationDays = getNumberOfVacationDays(numberVacationDays, startDay);
        double vacationPayment = (double) averageSalary /12 / 29.3 * numberVacationDays;
        return String.valueOf(vacationPayment);
    }

    private int getNumberOfVacationDays(int numberVacationDays,LocalDate startDay){
        LocalDate endDay = startDay.plusDays(numberVacationDays - 1);
        int year = startDay.getYear();
        Map<Month, List<Integer>> holidays = Holidays.getHolidays();
        for(Map.Entry<Month, List<Integer>> entry : holidays.entrySet()){
            Month month = entry.getKey();
            List<Integer> daysHolidays = entry.getValue();
            for(int j = 0; j < daysHolidays.size(); j++){
                int day =  daysHolidays.get(j);
                LocalDate holiday;

                if(endDay.getYear() == startDay.getYear()){
                    holiday = LocalDate.of(year, month, day);
                    if((holiday.isEqual(startDay) || holiday.isEqual(endDay)) || ( holiday.isAfter(startDay) && holiday.isBefore(endDay))){
                        numberVacationDays --;
                    }
                } else {
                    if(month.getValue() < startDay.getMonthValue()){
                        holiday = LocalDate.of(year + 1, month, day);
                    } else {
                        holiday = LocalDate.of(year, month, day);
                    }
                    if((holiday.isEqual(startDay) || holiday.isEqual(endDay)) || ( holiday.isAfter(startDay) && holiday.isBefore(endDay))){
                        numberVacationDays --;
                    }
                }
            }
        }
        return numberVacationDays;
    }


}
