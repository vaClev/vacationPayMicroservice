package com.olegvas.springboot.microservice.example.holidaymoney.spring_boot_microservice_holidaymoney_service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class HolidayMoneyCalculator {
    private double averageMonthlySalary;
    private double averageDailySalary;
    private static final double AVARAGE_DAYS_IN_MOUNTH_RUSSIA = 29.3; //(365 дн. – 14 дн.)/ 12 мес. Вычитается именно 14 дней, т.к. столько в РФ официальных праздничных дней в году (ст. 112 ТК РФ).

    public HolidayMoneyCalculator(double averageMonthlySalary) {
        this.averageMonthlySalary = averageMonthlySalary;
        this.averageDailySalary = averageMonthlySalary / AVARAGE_DAYS_IN_MOUNTH_RUSSIA;
    }
    public HolidayMoneyCalculator(String salary) {
        this(parseIntFields(salary));
    }
    public double getHolidayMoney(int days) {
        double holidayMoney = this.averageDailySalary * days;
        return Math.round(holidayMoney * 100.0) / 100.0; //округление до целых копеек (центов)
    }


    public double getHolidayMoney(String days) {
        return getHolidayMoney(parseIntFields(days));
    }
    public double getHolidayMoney(String firstDay, String lastDay) {
        int usualDays = getUsualDaysCount(firstDay, lastDay);
        return getHolidayMoney(usualDays);
    }


    private int getUsualDaysCount(String firstDay, String lastDay) {
        LocalDate holidayFirstDay = parseDateString(firstDay);
        LocalDate holidayLastDay = parseDateString(lastDay);
        return getUsualDaysCount(holidayFirstDay, holidayLastDay);
    }
    private int getUsualDaysCount(LocalDate holidayFirstDay, LocalDate holidayLastDay) {
        if (holidayFirstDay.isAfter(holidayLastDay)) {
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


    private static LocalDate parseDateString(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            System.out.println("ошибка в формате запроса, недопустимые символы: " + date);
            return LocalDate.of(1970, 1, 1);
        }
    }
    private static int parseIntFields(String intString) {
        try {
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            System.out.println("ошибка в формате запроса, недопустимые символы: " + intString);
            return -1;
        }
    }
}
