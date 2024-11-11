package com.contas.contas.infrastructure.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AccountRequest(List<String> namesAccounts,
                             @NotBlank(message = "Usuário é obrigatório")
                             String user) {
}
