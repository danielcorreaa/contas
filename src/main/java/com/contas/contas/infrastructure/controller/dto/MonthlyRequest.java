package com.contas.contas.infrastructure.controller.dto;

import java.math.BigDecimal;

public record MonthlyRequest(String idMonthly, String idAccount, BigDecimal valor, Boolean check) {
}
