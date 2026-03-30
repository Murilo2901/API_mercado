package com.mercado.API.Controller;


import com.mercado.API.Model.Produto;
import com.mercado.API.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
@Autowired
    private ProdutoService produtoService;

@PostMapping
    public Produto cadastrar(@RequestBody Produto produto){
    return produtoService.salvar(produto);
}

@GetMapping
    public List<Produto> listarTodos(){
    return produtoService.buscarTodos();
}

@PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto){
    return produtoService.atualizar(id, produto);
}

@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
    produtoService.deletar(id);
}
}
