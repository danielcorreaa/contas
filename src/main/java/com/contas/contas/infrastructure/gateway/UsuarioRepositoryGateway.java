package com.contas.contas.infrastructure.gateway;


import com.contas.contas.application.gateway.UsuarioGateway;
import com.contas.contas.config.exceptions.NotFoundException;
import com.contas.contas.domain.Usuario;
import com.contas.contas.infrastructure.gateway.mapper.UsuarioDocumentMapper;
import com.contas.contas.infrastructure.persistence.document.UsuarioDocument;
import com.contas.contas.infrastructure.persistence.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryGateway implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioDocumentMapper usuarioDocumentMapper;

    public UsuarioRepositoryGateway(UsuarioRepository usuarioRepository, UsuarioDocumentMapper usuarioDocumentMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioDocumentMapper = usuarioDocumentMapper;
    }

    @Override
    public Usuario findById(String login) {
        UsuarioDocument usuarioDocument = usuarioRepository.findById(login)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));
        return usuarioDocumentMapper.toUsuario(usuarioDocument);
    }

    @Override
    public Usuario create(Usuario usuario) {
        UsuarioDocument usuarioDocument = usuarioRepository.save(usuarioDocumentMapper.toUsuarioDocument(usuario));
        return usuarioDocumentMapper.toUsuario(usuarioDocument);
    }
}
