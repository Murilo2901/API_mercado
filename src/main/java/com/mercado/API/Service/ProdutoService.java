package com.mercado.API.Service;

import com.mercado.API.DTO.ProdutoDTO.ProdutoRequestDTO;
import com.mercado.API.DTO.ProdutoDTO.ProdutoResponseDTO;
import com.mercado.API.Model.Produto;
import com.mercado.API.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired private ProdutoRepository repository;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return repository.findByAtivoTrue().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProdutoResponseDTO> listarPorCategoria(String categoria) {
        return repository.findByCategoriaAndAtivoTrue(categoria)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        p.setNome(dto.getNome());
        p.setDescricao(dto.getDescricao());
        p.setCategoria(dto.getCategoria());
        p.setPreco(dto.getPreco());
        p.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        p.setImagemUrl(dto.getImagemUrl());
        return toDTO(repository.save(p));
    }

    public void deletar(Long id) {
        Produto p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        p.setAtivo(false);
        repository.save(p);
    }

    private Produto toEntity(ProdutoRequestDTO dto) {
        Produto p = new Produto();
        p.setNome(dto.getNome());
        p.setDescricao(dto.getDescricao());
        p.setCategoria(dto.getCategoria());
        p.setPreco(dto.getPreco());
        p.setQuantidadeEstoque(dto.getQuantidadeEstoque() != null ? dto.getQuantidadeEstoque() : 0);
        p.setImagemUrl(dto.getImagemUrl());
        return p;
    }

    private ProdutoResponseDTO toDTO(Produto p) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setDescricao(p.getDescricao());
        dto.setCategoria(p.getCategoria());
        dto.setPreco(p.getPreco());
        dto.setQuantidadeEstoque(p.getQuantidadeEstoque());
        dto.setImagemUrl(p.getImagemUrl());
        dto.setAtivo(p.getAtivo());
        return dto;
    }
}