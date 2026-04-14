package br.edu.ifsc.fln.integrador.repository;

import br.edu.ifsc.fln.integrador.domain.ArquivoImagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoImagemRepository extends JpaRepository<ArquivoImagem, Integer> {
}
