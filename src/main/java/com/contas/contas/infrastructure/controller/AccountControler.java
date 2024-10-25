package com.contas.contas.infrastructure.controller;

import com.contas.contas.application.gateway.AccountGateway;
import com.contas.contas.application.usecase.AccountUseCase;
import com.contas.contas.application.usecase.interactor.AccountInteractorUseCase;
import com.contas.contas.config.response.Result;
import com.contas.contas.domain.Account;
import com.contas.contas.infrastructure.controller.dto.AccountDto;
import com.contas.contas.infrastructure.controller.mapper.AccountApiMapper;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
@CrossOrigin
public class AccountControler {

    private final AccountGateway accountGateway;
    private final AccountApiMapper accountApiMapper;

    public AccountControler(AccountGateway accountGateway, AccountApiMapper accountApiMapper) {
        this.accountGateway = accountGateway;
        this.accountApiMapper = accountApiMapper;
    }


    @PostMapping
    public ResponseEntity<Result<List<AccountDto>>> insert( @RequestBody List<String> namesAccounts){
        AccountUseCase accountUseCase  = new AccountInteractorUseCase(accountGateway);
        var accounts = namesAccounts.stream().map(Account::new).toList();
        var accountsResponse = accountUseCase.saveAll(accounts);
        return ResponseEntity.ok(Result.ok(accountApiMapper.toAccountsDto(accountsResponse)));
    }

    @PutMapping("inactive/{id}")
    public ResponseEntity<Result<AccountDto>> inactive(@PathVariable String id){
        AccountUseCase accountUseCase  = new AccountInteractorUseCase(accountGateway);
        var accountsResponse =  accountUseCase.inactive(id);
        return ResponseEntity.ok(Result.ok(accountApiMapper.toAccountDto(accountsResponse)));
    }

    @GetMapping("/{active}")
    public ResponseEntity<Result<List<AccountDto>>> findAll(@PathVariable Boolean active){
        AccountUseCase accountUseCase  = new AccountInteractorUseCase(accountGateway);
        List<Account> accountsResponse = null;
        if(Boolean.TRUE.equals(active)){
            accountsResponse = accountUseCase.findAllActive();
        } else {
            accountsResponse = accountUseCase.findAll();
        }

        return ResponseEntity.ok(Result.ok(accountApiMapper.toAccountsDto(accountsResponse)));
    }

}
