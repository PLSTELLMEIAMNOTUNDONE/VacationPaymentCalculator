package org.example.calculator;


import jakarta.validation.Valid;
import org.example.vacation.VacationRequest;
import org.example.vacation.VacationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class CalculatorController {
    @Autowired
    private final Calculator calculator;

    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping(value = "/calculate",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public VacationResponse calculate(@RequestBody @Valid VacationRequest request) {
        if (request.getDates() == null)
            return calculator.calculateVacation(request.getSalary(), request.getDaysOfVacation());
        return calculator.calculateVacation(request.getSalary(), request.getDaysOfVacation(), request.getDates());

    }
}
