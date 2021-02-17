package com.luanreis.cursosb.repositories;

import com.luanreis.cursosb.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {

}
