package br.com.fiap.coletalixo.dto;

import br.com.fiap.coletalixo.models.Usuario;

public record UsuarioExibicaoDto(
        Integer id,
        String nome,
        String email) {

    public UsuarioExibicaoDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
