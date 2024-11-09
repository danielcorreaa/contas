package com.contas.contas.domain;

import com.contas.contas.config.exceptions.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.*;

public class Monthly {

    private String id;

    private LocalDate createdDate;
    private LocalDate updateDate;
    private List<Account> accounts;

    private String user;

    public Monthly(String id, String user, List<Account> accounts) {
        if(accounts.isEmpty()){
            throw new BusinessException("Must exists be at least one account");
        }
        this.createdDate = LocalDate.now();
        this.user = user;
        this.id =  id;
        this.accounts = accounts;
    }

    private Monthly(String id, LocalDate createdDate, LocalDate updateDate, List<Account> accounts, String user) {
        this.id = id;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.accounts = accounts;
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void clearAccount() {
        this.accounts = new ArrayList<>();
    }

    public static Monthly copy(String id, LocalDate createdDate, LocalDate updateDate, List<Account> accounts, String user){
        return new Monthly(id, createdDate, updateDate, accounts, user);
    }


}
