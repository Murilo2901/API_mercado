package com.mercado.API.DAO;

import com.mercado.API.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}
