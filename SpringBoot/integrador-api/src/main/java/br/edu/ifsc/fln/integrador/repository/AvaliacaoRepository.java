package br.edu.ifsc.fln.integrador.repository;

import br.edu.ifsc.fln.integrador.domain.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}
