package com.contas.contas.application.usecase.interactor;

import com.contas.contas.application.gateway.MonthlyGateway;
import com.contas.contas.application.usecase.AccountUseCase;
import com.contas.contas.application.usecase.MonthlyUseCase;
import com.contas.contas.config.exceptions.BusinessException;
import com.contas.contas.config.exceptions.NotFoundException;
import com.contas.contas.domain.Account;
import com.contas.contas.domain.Monthly;
import com.contas.contas.domain.MonthlyId;
import com.contas.contas.infrastructure.controller.dto.MonthlyRequest;
import com.contas.contas.infrastructure.dto.Pagination;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MonthlyInteractorUseCase implements MonthlyUseCase {

    private final MonthlyGateway monthlyGateway;
    private final AccountUseCase accountUseCase;

    public MonthlyInteractorUseCase(MonthlyGateway monthlyGateway, AccountUseCase accountUseCase) {
        this.monthlyGateway = monthlyGateway;
        this.accountUseCase = accountUseCase;
    }

    @Override
    public Optional<Monthly> findById(String id) {
        return monthlyGateway.findById(id);
    }

    @Override
    public Monthly createAccountByMonthly() {
        var maybeMonthLy = findById(MonthlyId.getId(LocalDate.now()));
        if(maybeMonthLy.isPresent()){
            throw new BusinessException("Monthly already exist for month");
        }
        List<Account> accounts = accountUseCase.findAllActive();
        Monthly monthly = new Monthly(accounts);
        return monthlyGateway.save(monthly);
    }

    @Override
    public Monthly edit(Monthly monthly) {
        return null;
    }

    @Override
    public Pagination findAll(int page, int size) {
        return monthlyGateway.findAll(page, size);
    }

    @Override
    public Monthly checkAccount(MonthlyRequest request) {
        Monthly monthly = findById(request.idMonthly()).orElseThrow(() -> new NotFoundException("Monthly not found!"));
        Optional<Account> maybeAccount = monthly.getAccounts().stream().filter(account -> account.getId().equals(request.idAccount())).findFirst();
        maybeAccount.ifPresent( account -> account.checkAccount(request.check(), request.valor()));
        return monthlyGateway.save(monthly);
    }
}
