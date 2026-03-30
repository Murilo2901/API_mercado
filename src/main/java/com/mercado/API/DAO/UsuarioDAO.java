package com.mercado.API.DAO;

import com.mercado.API.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
}