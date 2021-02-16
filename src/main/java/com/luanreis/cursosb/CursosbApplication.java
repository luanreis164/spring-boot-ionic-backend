package com.luanreis.cursosb;

import com.luanreis.cursosb.domain.Categoria;
import com.luanreis.cursosb.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursosbApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;



	public static void main(String[] args) {
		SpringApplication.run(CursosbApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));





	}


}
