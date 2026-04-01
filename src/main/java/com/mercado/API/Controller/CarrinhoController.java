package com.mercado.API.Controller;

import com.mercado.API.DTO.CarrinhoDTO.CarrinhoRequestDTO;
import com.mercado.API.DTO.CarrinhoDTO.CarrinhoResponseDTO;
import com.mercado.API.Service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoController {

    @Autowired private CarrinhoService service;

    @GetMapping("/{usuarioId}")
    public List<CarrinhoResponseDTO> listar(@PathVariable Long usuarioId) {
        return service.listar(usuarioId);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionar(@RequestBody CarrinhoRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.adicionar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("sucesso", false, "mensagem", e.getMessage()));
        }
    }

    @PutMapping("/{carrinhoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long carrinhoId, @RequestParam Integer quantidade) {
        try {
            var item = service.atualizar(carrinhoId, quantidade);
            return item != null
                    ? ResponseEntity.ok(item)
                    : ResponseEntity.ok(Map.of("sucesso", true, "mensagem", "Item removido"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("sucesso", false, "mensagem", e.getMessage()));
        }
    }

    @DeleteMapping("/{carrinhoId}")
    public ResponseEntity<?> remover(@PathVariable Long carrinhoId) {
        service.remover(carrinhoId);
        return ResponseEntity.ok(Map.of("sucesso", true, "mensagem", "Item removido"));
    }

    @DeleteMapping("/limpar/{usuarioId}")
    public ResponseEntity<?> limpar(@PathVariable Long usuarioId) {
        service.limpar(usuarioId);
        return ResponseEntity.ok(Map.of("sucesso", true, "mensagem", "Carrinho limpo"));
    }
}