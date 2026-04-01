package com.mercado.API.Service;

import com.mercado.API.DTO.UsuarioDTO.LoginRequestDTO;
import com.mercado.API.DTO.UsuarioDTO.LoginResponseDTO;
import com.mercado.API.DTO.UsuarioDTO.UsuarioRequestDTO;
import com.mercado.API.DTO.UsuarioDTO.UsuarioResponseDTO;
import com.mercado.API.Model.Usuario;
import com.mercado.API.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        if (repository.existsByUsuario(normalizeUsuario(dto.getUsuario()))) {
            throw new RuntimeException("Nome de usuário já existe");
        }
        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setUsuario(normalizeUsuario(dto.getUsuario()));
        u.setSenha(dto.getSenha()); // senha salva diretamente
        u.setPerfil(dto.getPerfil() != null ? dto.getPerfil() : "CLIENTE");

        return toDTO(repository.save(u));
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        Usuario u = repository
                .findByUsuario(normalizeUsuario(dto.getUsuario()))
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!dto.getSenha().equals(u.getSenha())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return new LoginResponseDTO(
                true,
                "Login realizado com sucesso!",
                u.getId(),
                u.getNome(),
                u.getUsuario()
        );
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private String normalizeUsuario(String usuario) {
        if (usuario == null) return null;
        return usuario.startsWith("@") ? usuario : "@" + usuario;
    }

    private UsuarioResponseDTO toDTO(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setUsuario(u.getUsuario());
        dto.setPerfil(u.getPerfil());
        return dto;
    }
}