package br.edu.ifsc.fln.integrador.repository;

import br.edu.ifsc.fln.integrador.domain.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
}
