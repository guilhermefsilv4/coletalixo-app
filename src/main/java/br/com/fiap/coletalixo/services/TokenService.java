package br.com.fiap.coletalixo.services;

import br.com.fiap.coletalixo.models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${minha.chave.secreta}")
    private String palavraSecreta;

    public String gerarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            return JWT.create()
                    .withIssuer("agendamentos")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Não foi possível gerar o token!");
        }

    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            return JWT.require(algorithm)
                    .withIssuer("agendamentos")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }
    }

    private Instant gerarDataDeExpiracao() {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}