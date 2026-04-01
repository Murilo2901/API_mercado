package com.mercado.API.Service;

import com.mercado.API.DTO.VendaDTO.VendaItemDTO;
import com.mercado.API.DTO.VendaDTO.VendaRequestDTO;
import com.mercado.API.DTO.VendaDTO.VendaResponseDTO;
import com.mercado.API.Model.*;
import com.mercado.API.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired private VendaRepository vendaRepository;
    @Autowired private VendaItemRepository vendaItemRepository;
    @Autowired private CarrinhoRepository carrinhoRepository;
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Transactional
    public VendaResponseDTO finalizarPedido(VendaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<CarrinhoItem> itensCarrinho = carrinhoRepository.findByUsuarioId(dto.getUsuarioId());
        if (itensCarrinho.isEmpty()) {
            throw new RuntimeException("Carrinho está vazio");
        }

        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setDataVenda(LocalDate.now());
        venda.setStatusVenda("CONFIRMADA");
        BigDecimal total = BigDecimal.ZERO;

        for (CarrinhoItem ci : itensCarrinho) {
            Produto produto = ci.getProduto();

            if (produto.getQuantidadeEstoque() < ci.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para: " + produto.getNome());
            }

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - ci.getQuantidade());
            produtoRepository.save(produto);

            VendaItem vi = new VendaItem();
            vi.setVenda(venda);
            vi.setProduto(produto);
            vi.setQuantidade(ci.getQuantidade());
            vi.setPrecoUnit(produto.getPreco());
            venda.getItens().add(vi);

            total = total.add(produto.getPreco().multiply(BigDecimal.valueOf(ci.getQuantidade())));
        }

        venda.setTotal(total);
        Venda salva = vendaRepository.save(venda);

        carrinhoRepository.deleteAllByUsuarioId(dto.getUsuarioId());

        return toDTO(salva);
    }

    public List<VendaResponseDTO> listarTodas() {
        return vendaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<VendaResponseDTO> listarPorUsuario(Long usuarioId) {
        return vendaRepository.findByUsuarioIdOrderByDataVendaDesc(usuarioId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    private VendaResponseDTO toDTO(Venda v) {
        VendaResponseDTO dto = new VendaResponseDTO();
        dto.setId(v.getId());
        dto.setUsuarioId(v.getUsuario().getId());
        dto.setNomeUsuario(v.getUsuario().getNome());
        dto.setTotal(v.getTotal());
        dto.setDataVenda(v.getDataVenda());
        dto.setStatusVenda(v.getStatusVenda());
        dto.setItens(v.getItens().stream().map(i -> {
            VendaItemDTO vi = new VendaItemDTO();
            vi.setProdutoId(i.getProduto().getId());
            vi.setNomeProduto(i.getProduto().getNome());
            vi.setQuantidade(i.getQuantidade());
            vi.setPrecoUnit(i.getPrecoUnit());
            return vi;
        }).collect(Collectors.toList()));
        return dto;
    }
}