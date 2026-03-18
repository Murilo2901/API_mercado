package com.mercado.API.Service;

import com.mercado.API.DAO.UsuarioDAO;
import com.mercado.API.Domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

    public Usuario salvar(Usuario usuario){
        return usuarioDAO.save(usuario);
    }

    public List<Usuario> buscarTodos(){
        return usuarioDAO.findAll();
    }
}
