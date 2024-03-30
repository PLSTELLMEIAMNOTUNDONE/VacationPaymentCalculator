package org.example.vacation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * json object class
 * corresponds to structure below
 * {
 * "salary" : "(number)",
 * "daysOfVacation" : "(number)",
 * "dates" : [[]...[]]
 * }
 * every array in "dates" must have length == 2 and denominate dates of start and end of vacation, respectively, in format yyyy-MM-dd
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class VacationRequest {
    @NotNull @Positive
    long salary;
    @NotNull @PositiveOrZero
    long daysOfVacation;
    @VacationDateValidator
    String[][] dates;
}
