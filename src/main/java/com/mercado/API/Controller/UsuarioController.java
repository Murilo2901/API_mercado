package com.mercado.API.Controller;

import com.mercado.API.DTO.UsuarioDTO.LoginRequestDTO;
import com.mercado.API.DTO.UsuarioDTO.UsuarioRequestDTO;
import com.mercado.API.DTO.UsuarioDTO.UsuarioResponseDTO;
import com.mercado.API.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired private UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.salvar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("sucesso", false, "mensagem", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.login(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("sucesso", false, "mensagem", e.getMessage()));
        }
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok(Map.of("sucesso", true, "mensagem", "Usuário removido"));
    }
}