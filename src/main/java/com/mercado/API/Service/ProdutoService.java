package com.mercado.API.Service;

import com.mercado.API.Repository.ProdutoRepository;
import com.mercado.API.Model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoDAO;

    public Produto salvar(Produto produto){
        return produtoDAO.save(produto);
    }

    public List<Produto> buscarTodos(){
        return produtoDAO.findAll();
    }

    public Produto atualizar(Long id, Produto produtoAtualizado){
        Produto produtoExistente = produtoDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encotrado"));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
        return produtoDAO.save(produtoExistente);
    }

    public void deletar(Long id){
        produtoDAO.deleteById(id);
    }
}
