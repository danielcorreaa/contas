package com.contas.contas.application.usecase.interactor;

import com.contas.contas.application.gateway.AuthenticationGateway;
import com.contas.contas.application.gateway.TokenGateway;
import com.contas.contas.application.usecase.TokenUseCase;
import com.contas.contas.domain.Usuario;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUseCaseInteractor implements TokenUseCase {

    private final TokenGateway tokenGateway;
    private final AuthenticationGateway authenticationGateway;

    public TokenUseCaseInteractor(TokenGateway tokenGateway, AuthenticationGateway authenticationGateway) {
        this.tokenGateway = tokenGateway;
        this.authenticationGateway = authenticationGateway;
    }

    @Override
    public String gerarToken(Usuario usuario) {
        return tokenGateway.gerarToken(usuario);
    }

    @Override
    public Usuario autenticao(String login, String senha) {
        return authenticationGateway.autenticao(login, senha);
    }

    @Override
    public String recuperarToken(HttpServletRequest request) {
        return tokenGateway.recuperarToken(request);
    }

    @Override
    public String getSubject(String tokenJWT) {
        return tokenGateway.getSubject(tokenJWT);
    }
}
