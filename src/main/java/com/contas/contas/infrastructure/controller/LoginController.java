package com.contas.contas.infrastructure.controller;

import com.contas.contas.application.gateway.AuthenticationGateway;
import com.contas.contas.application.gateway.TokenGateway;
import com.contas.contas.application.gateway.UsuarioGateway;
import com.contas.contas.application.usecase.TokenUseCase;
import com.contas.contas.application.usecase.UsuarioUseCase;
import com.contas.contas.application.usecase.interactor.TokenUseCaseInteractor;
import com.contas.contas.application.usecase.interactor.UsuarioInteractorUseCase;
import com.contas.contas.config.response.Result;
import com.contas.contas.domain.Usuario;
import com.contas.contas.infrastructure.controller.dto.AutenticacaoRequest;
import com.contas.contas.infrastructure.controller.dto.UsuarioDto;
import com.contas.contas.infrastructure.controller.mapper.UsuarioApiMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Tag(name = "Login API")
@CrossOrigin
public class LoginController {

    private final TokenGateway tokenGateway;
    private final AuthenticationGateway authenticationGateway;
    private final UsuarioGateway usuarioGateway;
    private final UsuarioApiMapper usuarioApiMapper;

    public LoginController(TokenGateway tokenGateway, AuthenticationGateway authenticationGateway, UsuarioGateway usuarioGateway, UsuarioApiMapper usuarioApiMapper) {
        this.tokenGateway = tokenGateway;
        this.authenticationGateway = authenticationGateway;
        this.usuarioGateway = usuarioGateway;
        this.usuarioApiMapper = usuarioApiMapper;
    }

    @PostMapping
    public ResponseEntity<Result<String>> login(@RequestBody AutenticacaoRequest request){
        TokenUseCase tokenUseCase = new TokenUseCaseInteractor(tokenGateway, authenticationGateway);
        Usuario autenticao = tokenUseCase.autenticao(request.login(), request.senha());
        var tokenJWT = tokenUseCase.gerarToken(autenticao);
        return ResponseEntity.ok(Result.ok(tokenJWT));
    }

    @PostMapping("/user/create")
    public ResponseEntity<Result<UsuarioDto>> create(@RequestBody UsuarioDto request){
        UsuarioUseCase usuarioUseCase = new UsuarioInteractorUseCase(usuarioGateway);
        Usuario usuario = usuarioUseCase.create(usuarioApiMapper.toUsuario(request));
        return ResponseEntity.ok(Result.ok(usuarioApiMapper.toUsuarioDto(usuario)));
    }
}
