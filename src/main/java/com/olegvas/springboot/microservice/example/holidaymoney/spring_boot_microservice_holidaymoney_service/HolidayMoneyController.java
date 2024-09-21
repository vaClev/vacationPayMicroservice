package com.olegvas.springboot.microservice.example.holidaymoney.spring_boot_microservice_holidaymoney_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolidayMoneyController {
    @Autowired
    private Environment environment;

    @CrossOrigin
    @GetMapping("/holiday-money/averageSalary/{salary}/for/{days}")
    public double getHolidayMoneySum(@PathVariable String salary,
                     @PathVariable String days) {
        return new HolidayMoneyCalculator(salary).getHolidayMoney(days);
    }

    @CrossOrigin
    @GetMapping("/holiday-money/averageSalary/{salary}/firstDay/{firstDay}/lastDay/{lastDay}")
    public double getHolidayMoneySum(@PathVariable String salary,
                                     @PathVariable String firstDay, @PathVariable String lastDay) {
        return new HolidayMoneyCalculator(salary).getHolidayMoney(firstDay, lastDay);
    }
}
