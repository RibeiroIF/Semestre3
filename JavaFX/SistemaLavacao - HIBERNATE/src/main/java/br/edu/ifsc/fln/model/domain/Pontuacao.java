package br.edu.ifsc.fln.model.domain;

import br.edu.ifsc.fln.exception.ExceptionLavacao;

import jakarta.persistence.*;

@Entity
@Table(name = "pontuacao")
public class Pontuacao {

	@Id
	@Column(name = "id_cliente")
	private Integer id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	private int quantidade = 0;
	
	public Pontuacao() {
		super();
		this.quantidade = 0;
	}
	
	public void somarPontos(int quantidade) {
		this.quantidade += quantidade;
	}

	// EXCEÇÃO PARA CASO SEJA SUBTRAÍDO UM VALOR MAIOR QUE A QUANTIDADE ATUAL OU A QUANTIDADE ESTEJA EM ZERO
	public void subtrairPontos(int quantidade) throws ExceptionLavacao {
		if (this.quantidade == 0 || quantidade >= this.quantidade) {
			throw new ExceptionLavacao("Impossível retirar os pontos deferidos");
		}
		else {
			this.quantidade -= quantidade;
		}
	}
	
	public int verificarPontos() {
		return quantidade;
	}
}
