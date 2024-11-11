package com.contas.contas.application.gateway;


import com.contas.contas.domain.Usuario;

public interface UsuarioGateway {
    Usuario findById(String matricula);

    Usuario create(Usuario usuario);
}
