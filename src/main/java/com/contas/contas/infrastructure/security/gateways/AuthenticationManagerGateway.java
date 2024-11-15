package com.contas.contas.infrastructure.security.gateways;


import com.contas.contas.application.gateway.AuthenticationGateway;
import com.contas.contas.domain.Usuario;
import com.contas.contas.infrastructure.persistence.document.UsuarioDocument;
import com.contas.contas.infrastructure.security.mapper.AuthenticationMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerGateway implements AuthenticationGateway {


    private final AuthenticationManager manager;

    private final AuthenticationMapper authenticationMapper;

    public AuthenticationManagerGateway(AuthenticationManager manager, AuthenticationMapper authenticationMapper) {
        this.manager = manager;
        this.authenticationMapper = authenticationMapper;
    }

    public Usuario autenticao(String login, String senha){
        var authenticationToken = new UsernamePasswordAuthenticationToken(login, senha);
        var authentication = manager.authenticate(authenticationToken);
        return authenticationMapper.toUsuario((UsuarioDocument) authentication.getPrincipal());
    }
}
