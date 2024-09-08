package com.olegvas.springboot.microservice.example.holidaymoney.spring_boot_microservice_holidaymoney_service;

import java.util.Calendar;

public class HolidayMoneyCalculator {
    private int yearPayment;
    public HolidayMoneyCalculator(int yearPayment)
    {
        this.yearPayment=yearPayment;
    }
    public double getHolidayMoneyThisYear(int days)
    {
        final int daysInThisYear = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR);
        double holidayMoney = (double)yearPayment/daysInThisYear*days;
        return Math.round(holidayMoney * 100.0) / 100.0; //округление до целых копеек (центов)
    }

    public static double getHolidayMoneyThisYear(String payment, String days)
    {
        int daysHoliday;
        int yearPayment;
        try {
            yearPayment = Integer.parseInt(payment);
            daysHoliday = Integer.parseInt(days);
        } catch (NumberFormatException e) {
            System.out.println("ошибка в формате запроса, недопустимые символы: "+payment+" "+days);
            return -1;
        }
        HolidayMoneyCalculator calc = new HolidayMoneyCalculator(yearPayment);
        return calc.getHolidayMoneyThisYear(daysHoliday);
    }
}
