package com.contas.contas.application.gateway;

import com.contas.contas.domain.Usuario;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenGateway {

    String gerarToken(Usuario usuario);

    String getSubject(String tokenJWT);

    String recuperarToken(HttpServletRequest request);
}
