package com.mercado.API.Mapper;

import com.mercado.API.DTO.ProdutoDTO.ProdutoRequestDTO;
import com.mercado.API.DTO.ProdutoDTO.ProdutoResponseDTO;
import com.mercado.API.Model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequestDTO dto) {
        Produto p = new Produto(); p.setNome(dto.getNome()); p.setCategoria(dto.getCategoria()); p.setPreco(dto.getPreco()); p.setQuantidadeEstoque(dto.getQuantidadeEstoque()); return p;
    }
    public ProdutoResponseDTO toDTO(Produto entity) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO(); dto.setId(entity.getId()); dto.setNome(entity.getNome()); dto.setPreco(entity.getPreco()); dto.setQuantidadeEstoque(entity.getQuantidadeEstoque()); return dto;
    }
}


