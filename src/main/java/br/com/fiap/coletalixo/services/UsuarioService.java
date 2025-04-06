package br.com.fiap.coletalixo.services;

import br.com.fiap.coletalixo.dto.UsuarioCadastroDto;
import br.com.fiap.coletalixo.dto.UsuarioExibicaoDto;
import br.com.fiap.coletalixo.models.Usuario;
import br.com.fiap.coletalixo.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioDTO) {

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDto(usuarioSalvo);

    }
}
