package com.mercado.API.DAO;

import com.mercado.API.Model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaDAO extends JpaRepository<Venda, Long> {
    List<Venda> findByUsuarioId(Long usuarioId);
}