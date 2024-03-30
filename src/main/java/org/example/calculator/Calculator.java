package org.example.calculator;

import org.example.vacation.VacationResponse;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class Calculator {

    private final SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
    private final Map<Integer, Set<Integer>> holidays = Map.of(Calendar.MARCH, Set.of(8),
            Calendar.FEBRUARY, Set.of(23)); // список праздников может быть продолжен

    /**
     * Насколько я понял, отпускные рассчитываются как средняя зарплата за год умноженная на число дней отпуска
     */
    public VacationResponse calculateVacation(long salary, long daysOfVacation) {
        VacationResponse vacationResponse = new VacationResponse();
        vacationResponse.setVacationPayment(salary * daysOfVacation);
        return vacationResponse;
    }


    /**
     * Насколько я понял, в данном случае, нам необходимо проверить, сколько отпускных дней пришлись не не
     * выходные или праздники, и умножить данное число на среднюю зарплату сотрудника.
     * dates - массив где dates[i][0] - дата выхода в отпуск,dates[i][1] - даты выхода обратно на работу.
     * Формат дат - yyyy-MM-ddм
     */
    public VacationResponse calculateVacation(long salary, long daysOfVacation, String[][] dates) {
        VacationResponse vacationResponse = new VacationResponse();
        Calendar calendar = Calendar.getInstance();
        long paidDays = 0;
        for (String[] segment : dates) {
            Date end;
            try {
                calendar.setTime(sp.parse(segment[0]));
                end = sp.parse(segment[1]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            while (calendar.getTime().compareTo(end) <= 0) { //end included
                if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                        && !holidays.get(calendar.get(Calendar.MONTH))
                        .contains(calendar.get(Calendar.DAY_OF_MONTH))) {
                    paidDays++;
                }
                calendar.add(Calendar.DATE, 1); //след день

            }

        }
        vacationResponse.setVacationPayment(salary * paidDays);
        return vacationResponse;
    }
}
