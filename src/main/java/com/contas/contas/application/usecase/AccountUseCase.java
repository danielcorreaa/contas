package com.contas.contas.application.usecase;

import com.contas.contas.domain.Account;

import java.util.List;

public interface AccountUseCase {

    Account edit(Account account);

    Account inactive(String id);
    List<Account> saveAll(List<Account> account);
    List<Account> findAllActive();
    List<Account> findAll();
}
