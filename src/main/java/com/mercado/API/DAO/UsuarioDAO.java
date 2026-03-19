package com.mercado.API.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mercado.API.model.Usuario;
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
}