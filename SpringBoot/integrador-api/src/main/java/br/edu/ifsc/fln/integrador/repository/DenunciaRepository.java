package br.edu.ifsc.fln.integrador.repository;

import br.edu.ifsc.fln.integrador.domain.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {
}
