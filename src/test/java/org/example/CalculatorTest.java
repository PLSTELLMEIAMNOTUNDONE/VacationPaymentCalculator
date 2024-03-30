package org.example;

import org.example.calculator.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


public class CalculatorTest {
    @Autowired
    private Calculator calculator;

    @Test
    public void calculateVacationSimpleTest() {
        long salary = 100, daysOfVacation = 14;
        long ans = 1400;
        long actualAns = calculator.calculateVacation(salary, daysOfVacation).getVacationPayment();
        Assert.isTrue(actualAns == ans, "calculateVacation simple version if not working");
    }

    @Test
    public void calculateVacationComplexTest() {
        long salary = 100, daysOfVacation = 14;
        long ans = 1000;
        String[][] dates = new String[2][2];
        dates[0][0] = "2022-03-02";
        dates[0][1] = "2022-03-08";
        dates[1][0] = "2022-02-21";
        dates[1][1] = "2022-02-27";
        long actualAns = calculator.calculateVacation(salary, daysOfVacation, dates).getVacationPayment();
        Assert.isTrue(actualAns == ans, "calculateVacation complex version if not working " + actualAns);
    }

}
