package com.contas.contas.infrastructure.gateway.mapper;


import com.contas.contas.domain.Usuario;
import com.contas.contas.infrastructure.persistence.document.UsuarioDocument;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDocumentMapper {
    public Usuario toUsuario(UsuarioDocument usuarioDocument) {
        return Usuario.UsuarioBuilder.anUsuario()
                .email(usuarioDocument.getEmail())
                .matricula(usuarioDocument.getLogin()).senha(usuarioDocument.getSenha()).build();
    }

    public UsuarioDocument toUsuarioDocument(Usuario usuario) {
        return new UsuarioDocument(usuario.getMatricula(),
                usuario.getMatricula(), usuario.getSenha(),
                usuario.getEmail());
    }
}
