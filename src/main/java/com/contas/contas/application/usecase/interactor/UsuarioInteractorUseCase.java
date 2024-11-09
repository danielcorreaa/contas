package com.contas.contas.application.usecase.interactor;

import com.contas.contas.application.gateway.UsuarioGateway;
import com.contas.contas.application.usecase.UsuarioUseCase;
import com.contas.contas.domain.Usuario;

public class UsuarioInteractorUseCase implements UsuarioUseCase {

    private UsuarioGateway usuarioGateway;

    public UsuarioInteractorUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioGateway.create(usuario);
    }
}
