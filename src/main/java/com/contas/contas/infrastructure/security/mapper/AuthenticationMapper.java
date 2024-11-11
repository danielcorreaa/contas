package com.contas.contas.infrastructure.security.mapper;

import com.contas.contas.domain.Usuario;
import com.contas.contas.infrastructure.persistence.document.UsuarioDocument;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public Usuario toUsuario(UsuarioDocument usuarioDocument){
        return Usuario.UsuarioBuilder.anUsuario()
               // .senha(usuarioDocument.getSenha())
                .email(usuarioDocument.getEmail())
                .matricula(usuarioDocument.getLogin())
                .build();
    }
}
