package com.mercado.API.Service;

import com.mercado.API.DTO.CarrinhoDTO.*;
import com.mercado.API.Model.CarrinhoItem;
import com.mercado.API.Model.Produto;
import com.mercado.API.Model.Usuario;
import com.mercado.API.Repository.CarrinhoRepository;
import com.mercado.API.Repository.ProdutoRepository;
import com.mercado.API.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    @Autowired private CarrinhoRepository carrinhoRepository;
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    public List<CarrinhoResponseDTO> listar(Long usuarioId) {
        return carrinhoRepository.findByUsuarioId(usuarioId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CarrinhoResponseDTO adicionar(CarrinhoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        int qtd = dto.getQuantidade() != null ? dto.getQuantidade() : 1;

        Optional<CarrinhoItem> existente = carrinhoRepository
                .findByUsuarioIdAndProdutoId(dto.getUsuarioId(), dto.getProdutoId());

        CarrinhoItem item;
        if (existente.isPresent()) {
            item = existente.get();
            item.setQuantidade(item.getQuantidade() + qtd);
        } else {
            item = new CarrinhoItem();
            item.setUsuario(usuario);
            item.setProduto(produto);
            item.setQuantidade(qtd);
        }
        return toDTO(carrinhoRepository.save(item));
    }

    public CarrinhoResponseDTO atualizar(Long carrinhoId, Integer quantidade) {
        CarrinhoItem item = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado no carrinho"));

        if (quantidade <= 0) {
            carrinhoRepository.delete(item);
            return null;
        }
        item.setQuantidade(quantidade);
        return toDTO(carrinhoRepository.save(item));
    }

    public void remover(Long carrinhoId) {
        carrinhoRepository.deleteById(carrinhoId);
    }

    public void limpar(Long usuarioId) {
        carrinhoRepository.deleteAllByUsuarioId(usuarioId);
    }

    private CarrinhoResponseDTO toDTO(CarrinhoItem item) {
        CarrinhoResponseDTO dto = new CarrinhoResponseDTO();
        dto.setCarrinhoId(item.getId());
        dto.setProdutoId(item.getProduto().getId());
        dto.setNomeProduto(item.getProduto().getNome());
        dto.setDescricaoProduto(item.getProduto().getDescricao());
        dto.setPreco(item.getProduto().getPreco());
        dto.setQuantidade(item.getQuantidade());
        dto.setImagemUrl(item.getProduto().getImagemUrl());
        return dto;
    }
}