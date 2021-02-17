package com.luanreis.cursosb.repositories;

import com.luanreis.cursosb.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer> {

}
