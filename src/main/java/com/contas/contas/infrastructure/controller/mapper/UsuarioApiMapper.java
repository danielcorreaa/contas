package com.contas.contas.infrastructure.controller.mapper;

import com.contas.contas.domain.Usuario;
import com.contas.contas.infrastructure.controller.dto.UsuarioDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioApiMapper {

    private PasswordEncoder passwordEncoder;

    public UsuarioApiMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario toUsuario(UsuarioDto usuarioDto){
        return Usuario.UsuarioBuilder.anUsuario()
                 .email(usuarioDto.email())
                 .matricula(usuarioDto.login()).senha(passwordEncoder.encode(usuarioDto.senha())).build();
     }

    public UsuarioDto toUsuarioDto(Usuario usuario) {
         return new UsuarioDto(usuario.getEmail(), usuario.getMatricula(), null);
    }
}
