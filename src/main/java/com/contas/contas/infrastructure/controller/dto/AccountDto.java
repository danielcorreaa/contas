package com.contas.contas.infrastructure.controller.dto;

import java.math.BigDecimal;

public record AccountDto(String id, String name, Boolean active, BigDecimal valor, Boolean check) {
}
