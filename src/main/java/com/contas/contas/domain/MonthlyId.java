package com.contas.contas.domain;

import java.time.LocalDate;

import static java.lang.String.format;

public class MonthlyId {

    private MonthlyId() {
    }

    public static String getId(LocalDate actual, String usuario){
        return String.format("%s--%s--%s", actual.getMonth().name(), actual.getYear(), usuario);
    }

    public static String getId(String id, String usuario){
        return String.format("%s--%s", id, usuario);
    }
}
