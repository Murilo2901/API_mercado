package com.mercado.API.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mercado.API.model.Venda;
public interface VendaDAO extends JpaRepository<Venda, Long> {
}