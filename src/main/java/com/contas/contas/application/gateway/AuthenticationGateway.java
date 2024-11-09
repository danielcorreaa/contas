package com.contas.contas.application.gateway;

import com.contas.contas.domain.Usuario;

public interface AuthenticationGateway {

    Usuario autenticao(String login, String senha);
}
