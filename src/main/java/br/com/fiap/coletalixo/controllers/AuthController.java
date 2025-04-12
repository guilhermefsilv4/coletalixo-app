package br.com.fiap.coletalixo.controllers;

import br.com.fiap.coletalixo.dto.LoginDTO;
import br.com.fiap.coletalixo.dto.TokenDTO;
import br.com.fiap.coletalixo.dto.UsuarioCadastroDto;
import br.com.fiap.coletalixo.dto.UsuarioExibicaoDto;
import br.com.fiap.coletalixo.models.Usuario;
import br.com.fiap.coletalixo.services.TokenService;
import br.com.fiap.coletalixo.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO login(@RequestBody @Valid LoginDTO usuarioDto) {
        System.out.println("Requisição de login recebida");
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        usuarioDto.email(),
                        usuarioDto.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        return new TokenDTO(tokenService.gerarToken((Usuario) auth.getPrincipal()));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDTO) {
        System.out.println("Requisição de register recebida");
        return usuarioService.salvarUsuario(usuarioCadastroDTO);
    }
}
