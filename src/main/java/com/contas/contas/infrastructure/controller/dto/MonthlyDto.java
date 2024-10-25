package com.contas.contas.infrastructure.controller.dto;

import com.contas.contas.domain.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record MonthlyDto(
        String id,
        LocalDate createdDate,
        LocalDate updateDate,
        List<AccountDto> account, BigDecimal total) {
}
