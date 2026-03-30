package com.mercado.API.Controller;


import com.mercado.API.Model.Venda;
import com.mercado.API.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
@Autowired
    private VendaService vendaService;

@PostMapping
    public Venda registrarVenda(@RequestBody Venda venda){
    return vendaService.registrarVenda(venda);
}

@GetMapping("/usuario/{usuarioId}")
    public List<Venda> listarVendasDoUsuario(@PathVariable Long usuarioId){
    return vendaService.buscarVendasDoUsuario (usuarioId);
}
}
