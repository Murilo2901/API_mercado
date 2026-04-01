package com.mercado.API.Service;

import com.mercado.API.DTO.VendaDTO.VendaRequestDTO;
import com.mercado.API.DTO.VendaDTO.VendaResponseDTO;
import com.mercado.API.Model.Produto;
import com.mercado.API.Model.Usuario;
import com.mercado.API.Model.Venda;
import com.mercado.API.Repository.ProdutoRepository;
import com.mercado.API.Repository.UsuarioRepository;
import com.mercado.API.Repository.VendaRepository;
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

    // ── Finalizar pedido (converte carrinho em venda) ─────────
    @Transactional
    public VendaResponseDTO finalizarPedido(VendaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<CarrinhoItem> itensCarrinho = carrinhoRepository.findByUsuarioId(dto.getUsuarioId());
        if (itensCarrinho.isEmpty()) {
            throw new RuntimeException("Carrinho está vazio");
        }

        // Cria a venda
        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setDataVenda(LocalDate.now());
        venda.setStatusVenda("CONFIRMADA");
        BigDecimal total = BigDecimal.ZERO;

        // Processa cada item do carrinho
        for (CarrinhoItem ci : itensCarrinho) {
            Produto produto = ci.getProduto();

            // Verifica estoque
            if (produto.getQuantidadeEstoque() < ci.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para: " + produto.getNome());
            }

            // Desconta estoque
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - ci.getQuantidade());
            produtoRepository.save(produto);

            // Cria item da venda
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

        // Limpa o carrinho
        carrinhoRepository.deleteAllByUsuarioId(dto.getUsuarioId());

        return toDTO(salva);
    }

    // ── Listar todas as vendas ────────────────────────────────
    public List<VendaResponseDTO> listarTodas() {
        return vendaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ── Histórico por usuário ─────────────────────────────────
    public List<VendaResponseDTO> listarPorUsuario(Long usuarioId) {
        return vendaRepository.findByUsuarioIdOrderByDataVendaDesc(usuarioId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ── toDTO ─────────────────────────────────────────────────
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