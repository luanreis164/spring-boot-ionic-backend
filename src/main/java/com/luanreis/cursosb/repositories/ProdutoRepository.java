package com.luanreis.cursosb.repositories;

import com.luanreis.cursosb.domain.Categoria;
import com.luanreis.cursosb.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
