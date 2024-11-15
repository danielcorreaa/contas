package com.contas.contas.infrastructure.controller;

import com.contas.contas.application.gateway.AccountGateway;
import com.contas.contas.application.gateway.MonthlyGateway;
import com.contas.contas.application.usecase.AccountUseCase;
import com.contas.contas.application.usecase.MonthlyUseCase;
import com.contas.contas.application.usecase.interactor.AccountInteractorUseCase;
import com.contas.contas.application.usecase.interactor.MonthlyInteractorUseCase;
import com.contas.contas.config.exceptions.NotFoundException;
import com.contas.contas.config.response.Result;
import com.contas.contas.domain.Account;
import com.contas.contas.domain.Monthly;
import com.contas.contas.domain.MonthlyId;
import com.contas.contas.infrastructure.controller.dto.AccountDto;
import com.contas.contas.infrastructure.controller.dto.MonthlyDto;
import com.contas.contas.infrastructure.controller.dto.MonthlyRequest;
import com.contas.contas.infrastructure.controller.mapper.MonthlyApiMapper;
import com.contas.contas.infrastructure.dto.Pagination;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("monthly")
@CrossOrigin
public class MonthlyController {

    private final MonthlyGateway monthlyGateway;
    private final AccountGateway accountGateway;
    private final MonthlyApiMapper monthlyApiMapper;

    public MonthlyController(MonthlyGateway monthlyGateway, AccountGateway accountGateway, MonthlyApiMapper monthlyApiMapper) {
        this.monthlyGateway = monthlyGateway;
        this.accountGateway = accountGateway;
        this.monthlyApiMapper = monthlyApiMapper;
    }

    @PostMapping("{id}")
    public ResponseEntity<Result<MonthlyDto>> create(
            @Parameter(hidden = true) @RequestHeader(value = "user", required = false) String user,
            @PathVariable String id){
        AccountUseCase accountUseCase = new AccountInteractorUseCase(accountGateway);
        MonthlyUseCase monthlyUseCase  = new MonthlyInteractorUseCase(monthlyGateway, accountUseCase);
        Monthly accountByMonthly = monthlyUseCase.createAccountByMonthly(MonthlyId.getId(id, user));
        return ResponseEntity.ok(Result.ok(monthlyApiMapper.toMonthly(accountByMonthly)));
    }

    @PutMapping
    public ResponseEntity<Result<MonthlyDto>> checkAccount(@RequestBody MonthlyRequest request){
            AccountUseCase accountUseCase = new AccountInteractorUseCase(accountGateway);
        MonthlyUseCase monthlyUseCase  = new MonthlyInteractorUseCase(monthlyGateway, accountUseCase);
        Monthly accountByMonthly = monthlyUseCase.checkAccount(request);
        return ResponseEntity.ok(Result.ok(monthlyApiMapper.toMonthly(accountByMonthly)));
    }

    @GetMapping("{id}")
    public ResponseEntity<Result<MonthlyDto>> findById(
            @Parameter(hidden = true) @RequestHeader(value = "user", required = false) String user,
            @PathVariable String id){
        AccountUseCase accountUseCase = new AccountInteractorUseCase(accountGateway);
        MonthlyUseCase monthlyUseCase  = new MonthlyInteractorUseCase(monthlyGateway, accountUseCase);
        Monthly monthly = monthlyUseCase.findById(MonthlyId.getId(id, user)).orElseThrow(() -> new NotFoundException("Monthly not found"));
        return ResponseEntity.ok(Result.ok(monthlyApiMapper.toMonthly(monthly)));
    }

    @GetMapping("")
    public ResponseEntity<Result<List<MonthlyDto>>> findAll(@Parameter(hidden = true) @RequestHeader(value = "user", required = false) String user,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10")int size){
        AccountUseCase accountUseCase = new AccountInteractorUseCase(accountGateway);
        MonthlyUseCase monthlyUseCase  = new MonthlyInteractorUseCase(monthlyGateway, accountUseCase);
        Pagination pagination = monthlyUseCase.findAll(user, page, size);
        return ResponseEntity.ok(Result.ok(monthlyApiMapper.toMonthlies(pagination.getMonthlies()), pagination.getNext(), pagination.getTotal()));
    }
}
