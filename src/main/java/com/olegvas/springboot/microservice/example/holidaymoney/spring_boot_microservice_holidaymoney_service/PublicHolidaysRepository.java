package com.olegvas.springboot.microservice.example.holidaymoney.spring_boot_microservice_holidaymoney_service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class PublicHolidaysRepository {
    private static final ArrayList<LocalDate> holidays = new ArrayList<>(Arrays.asList(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2025, 1, 1),
            LocalDate.of(2025, 1, 7),
            LocalDate.of(2025, 3, 8)
    ));

    public static boolean isHolyday(LocalDate day) {
        return holidays.contains(day);
    }
}
