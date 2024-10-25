package com.contas.contas.application.gateway;

import com.contas.contas.domain.Account;

import java.util.List;

public interface AccountGateway {

    Account edit(Account account);
    List<Account> saveAll(List<Account> account);
    List<Account> findAllActive();

    Account inactive(String id);
    List<Account> findAll();
}
