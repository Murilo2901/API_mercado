package com.mercado.API.Controller;

import com.mercado.API.DTO.ProdutoDTO.ProdutoRequestDTO;
import com.mercado.API.DTO.ProdutoDTO.ProdutoResponseDTO;
import com.mercado.API.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired private ProdutoService service;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.salvar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProdutoResponseDTO> listarPorCategoria(@PathVariable String categoria) {
        return service.listarPorCategoria(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok(Map.of("sucesso", true, "mensagem", "Produto desativado"));
    }
}