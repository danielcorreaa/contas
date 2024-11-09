package com.contas.contas.infrastructure.controller.mapper;

import com.contas.contas.domain.Account;
import com.contas.contas.infrastructure.controller.dto.AccountDto;
import com.contas.contas.infrastructure.controller.dto.AccountRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountApiMapper {

    public AccountDto toAccountDto(Account account) {
        return new AccountDto( account.getId(), account.getName(), account.getActive(), account.getValor(), account.getCheck());
    }
    public List<AccountDto> toAccountsDto(List<Account> accounts) {
        return accounts.stream().map( this::toAccountDto).toList();
    }

    public List<Account> toAcconts(AccountRequest request) {
        return request.namesAccounts().stream().map( name -> new Account(name, request.user()))
                .collect(Collectors.toList());
    }
}
