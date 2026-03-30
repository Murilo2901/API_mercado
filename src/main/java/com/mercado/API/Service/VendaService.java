package com.mercado.API.Service;

import com.mercado.API.Repository.ProdutoRepository;
import com.mercado.API.Repository.VendaRepository;
import com.mercado.API.Model.Produto;
import com.mercado.API.Model.Venda;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {
    @Autowired private VendaRepository vendaDAO;
    @Autowired private ProdutoRepository produtoDAO;

    @Transactional
    public Venda registrarVenda(Venda venda){
        Produto produto = produtoDAO.findById(venda.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        if(produto.getQuantidadeEstoque() <venda.getQuantidadeComprada()){
            throw new RuntimeException("Estoque insuficiente!");
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() -venda.getQuantidadeComprada());
        produtoDAO.save(produto);

        venda.setDataVenda(LocalDate.now());
        venda.setStatusVenda("CONCLUIDA");
        return vendaDAO.save(venda);
    }

    public List<Venda> buscarVendasDoUsuario(Long usuarioId){
        return vendaDAO.findByUsuarioId(usuarioId);
    }

}
