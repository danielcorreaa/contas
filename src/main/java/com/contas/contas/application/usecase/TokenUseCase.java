package com.contas.contas.application.usecase;

import com.contas.contas.domain.Usuario;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenUseCase {

    String gerarToken(Usuario usuario);

    Usuario autenticao(String login, String senha);

    String recuperarToken(HttpServletRequest request);
    String getSubject(String tokenJWT);

}
