package com.contas.contas.application.usecase.interactor;

import com.contas.contas.application.gateway.AccountGateway;
import com.contas.contas.application.usecase.AccountUseCase;
import com.contas.contas.domain.Account;

import java.util.List;

public class AccountInteractorUseCase implements AccountUseCase {

    private AccountGateway accountGateway;

    public AccountInteractorUseCase(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public Account edit(Account account) {
        return null;
    }

    @Override
    public Account inactive(String id) {
        return accountGateway.inactive(id);
    }

    @Override
    public List<Account> saveAll(List<Account> account) {
        return accountGateway.saveAll(account);
    }



    @Override
    public List<Account> findAllActiveByUser(String user) {
        return accountGateway.findAllActive(user);
    }

    @Override
    public List<Account> findAllByUser(String user) {
        return accountGateway.findAll(user);
    }
}
