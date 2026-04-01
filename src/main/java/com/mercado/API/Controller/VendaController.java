package com.mercado.API.Controller;

import com.mercado.API.DTO.VendaDTO.VendaRequestDTO;
import com.mercado.API.DTO.VendaDTO.VendaResponseDTO;
import com.mercado.API.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    @Autowired private VendaService service;

    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizar(@RequestBody VendaRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.finalizarPedido(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("sucesso", false, "mensagem", e.getMessage()));
        }
    }

    @GetMapping
    public List<VendaResponseDTO> listar() {
        return service.listarTodas();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<VendaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }
}