package com.mercado.API.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mercado.API.model.Produto;
public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}
