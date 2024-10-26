package com.contas.contas.domain;

import com.contas.contas.config.exceptions.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.lang.String.*;

public class Monthly {

    private String id;

    private LocalDate createdDate;
    private LocalDate updateDate;
    private List<Account> accounts;

    public Monthly(List<Account> accounts) {
        if(accounts.isEmpty()){
            throw new BusinessException("Must exists be at least one account");
        }
        this.createdDate = LocalDate.now();
        this.id = MonthlyId.getId(this.createdDate);
        this.accounts = accounts;
    }

    public Monthly(String id, List<Account> accounts) {
        this.id = id;
        this.accounts = accounts;
    }

    public String getId() {
        return id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public BigDecimal getTotal(){
        return accounts.stream().map( account -> Optional.ofNullable(account.getValor()).orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
