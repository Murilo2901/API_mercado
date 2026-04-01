package com.mercado.API.Repository;

import com.mercado.API.Model.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoItem, Long> {
    List<CarrinhoItem> findByUsuarioId(Long usuarioId);
    Optional<CarrinhoItem> findByUsuarioIdAndProdutoId(Long usuarioId, Long produtoId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CarrinhoItem c WHERE c.usuario.id = :usuarioId")
    void deleteAllByUsuarioId(Long usuarioId);
}