package com.contas.contas.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyTest {

    @Test
    void createMonthlyId() {
       var accounts = List.of(new Account("Luz","user1"),new Account("Internet","user1"),new Account("Cartão","user1"));

       Monthly monthly = new Monthly("JANUARY--2024--user1","user1", accounts);

       assertEquals("JANUARY--2024--user1", monthly.getId());

       assertEquals(accounts, monthly.getAccounts());

    }
}