package com.contas.contas.domain;

import java.time.LocalDate;

import static java.lang.String.format;

public class MonthlyId {

    public static String getId(LocalDate actual){
        return String.format("%s--%s", actual.getMonth().name(), actual.getYear());
    }
}
