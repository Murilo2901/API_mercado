package com.mercado.API.Repository;

import com.mercado.API.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoriaAndAtivoTrue(String categoria);
    List<Produto> findByAtivoTrue();
}