package com.mercado.API.Mapper;

import com.mercado.API.DTO.UsuarioDTO.UsuarioRequestDTO;
import com.mercado.API.DTO.UsuarioDTO.UsuarioResponseDTO;
import com.mercado.API.Model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario u = new Usuario(); u.setNome(dto.getNome()); u.setEmail(dto.getEmail()); u.setSenha(dto.getSenha()); u.setPerfil(dto.getPerfil()); return u;
    }
    public UsuarioResponseDTO toDTO(Usuario entity) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO(); dto.setId(entity.getId()); dto.setNome(entity.getNome()); dto.setEmail(entity.getEmail()); dto.setPerfil(entity.getPerfil()); return dto;
    }
}
