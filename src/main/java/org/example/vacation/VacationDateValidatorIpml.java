package org.example.vacation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

/**
 * custom validation class, created for validation of field VacationRequest.dates
 * ensures that every array in "dates" have length == 2 and denominate dates of start and end of vacation, respectively, in format yyyy-MM-dd
 */
public class VacationDateValidatorIpml implements ConstraintValidator<VacationDateValidator, String[][]> {


    @Override
    public boolean isValid(String[][] strings, ConstraintValidatorContext constraintValidatorContext) {

        if (strings == null) return true;
        for (String[] dates : strings) {
            if (dates.length != 2 || !(dates[0].charAt(4) == '-') || !(dates[0].charAt(7) == '-') || !(dates[1].charAt(4) == '-') || !(dates[1].charAt(7) == '-')) {
                return false;
            }
            for (String date : dates) {
                String[] nums = date.split("-");
                for (String num : nums) {
                    if (!num.matches("[0-9]+")) {
                        return false;
                    }
                }
            }

        }
        return true;
    }
}
