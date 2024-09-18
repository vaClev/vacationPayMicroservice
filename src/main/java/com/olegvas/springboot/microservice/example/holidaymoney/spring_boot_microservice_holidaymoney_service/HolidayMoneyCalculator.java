package com.olegvas.springboot.microservice.example.holidaymoney.spring_boot_microservice_holidaymoney_service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

public class HolidayMoneyCalculator {
    private int yearPayment;

    public HolidayMoneyCalculator(int yearPayment) {
        this.yearPayment = yearPayment;
    }

    public double getHolidayMoneyThisYear(int days) {
        final int daysInThisYear = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR);
        double holidayMoney = (double) yearPayment / daysInThisYear * days;
        return Math.round(holidayMoney * 100.0) / 100.0; //округление до целых копеек (центов)
    }

    public static double getHolidayMoneyThisYear(String payment, String days) {
        int daysHoliday;
        int yearPayment;
        try {
            yearPayment = Integer.parseInt(payment);
            daysHoliday = Integer.parseInt(days);
        } catch (NumberFormatException e) {
            System.out.println("ошибка в формате запроса, недопустимые символы: " + payment + " " + days);
            return -1;
        }
        HolidayMoneyCalculator calc = new HolidayMoneyCalculator(yearPayment);
        return calc.getHolidayMoneyThisYear(daysHoliday);
    }

    public static double getHolidayMoneyThisYear(String payment, String firstDay, String lastDay) {
        LocalDate holidayFirstDay;
        LocalDate holidayLastDay;
        try {
            holidayFirstDay = LocalDate.parse(firstDay, DateTimeFormatter.ISO_DATE);
            holidayLastDay = LocalDate.parse(lastDay, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            System.out.println("ошибка в формате запроса, недопустимые символы: " + firstDay + " " + lastDay);
            return -1;
        }
        int days = calcUsualDays(holidayFirstDay, holidayLastDay);
        return getHolidayMoneyThisYear(payment, String.valueOf(days));
    }

    private static int calcUsualDays(LocalDate holidayFirstDay, LocalDate holidayLastDay) {
        if(holidayFirstDay.isAfter(holidayLastDay))
        {
            System.out.println("некоректные даты, дата начала больше даты окончания отпуска");
            return 0;
        }
        int usualDays = 0;
        LocalDate fistDayAfterVacation = holidayLastDay.plusDays(1);
        LocalDate pointer = holidayFirstDay.plusDays(0);
        do {
            if (!PublicHolidaysRepository.isHolyday(pointer)) {
                usualDays++;
            }
            pointer = pointer.plusDays(1);
        } while (!pointer.isEqual(fistDayAfterVacation));
        return usualDays;
    }
}
