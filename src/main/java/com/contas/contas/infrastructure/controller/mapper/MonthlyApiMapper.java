package com.contas.contas.infrastructure.controller.mapper;

import com.contas.contas.domain.Monthly;
import com.contas.contas.infrastructure.controller.dto.AccountDto;
import com.contas.contas.infrastructure.controller.dto.MonthlyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MonthlyApiMapper {

    private final AccountApiMapper accountApiMapper;

    public MonthlyApiMapper(AccountApiMapper accountApiMapper) {
        this.accountApiMapper = accountApiMapper;
    }

    public MonthlyDto toMonthly(Monthly monthly) {
        List<AccountDto> accountsDto = accountApiMapper.toAccountsDto(monthly.getAccounts());
        return new MonthlyDto(monthly.getId(), monthly.getCreatedDate(), monthly.getUpdateDate(), accountsDto, monthly.getTotal());
    }

    public List<MonthlyDto> toMonthlies(List<Monthly> monthlies) {
        return monthlies.stream().map(this::toMonthly).toList();
    }
}
