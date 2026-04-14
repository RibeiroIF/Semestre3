package br.edu.ifsc.fln.integrador.repository;

import br.edu.ifsc.fln.integrador.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
