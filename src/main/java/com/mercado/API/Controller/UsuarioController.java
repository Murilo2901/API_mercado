package com.mercado.API.Controller;

import com.mercado.API.Domain.Usuario;
import com.mercado.API.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
@Autowired
    private UsuarioService usuarioService;

@PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario){
    return usuarioService.salvar(usuario);
}

@GetMapping
    public List<Usuario> listarTodos(){
    return usuarioService.buscarTodos();
}
}
