package com.contas.contas.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyTest {

    @Test
    void createMonthlyId() {
       var accounts = List.of(new Account("Luz"),new Account("Internet"),new Account("Cartão"));

       Monthly monthly = new Monthly("JANUARY--2024", accounts);

       assertEquals("JANUARY--2024", monthly.getId());

       assertEquals(accounts, monthly.getAccounts());

    }
}