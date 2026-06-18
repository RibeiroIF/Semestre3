package br.edu.ifsc.fln.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "item_os")
public class ItemOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="valor_servico")
	private double valorServico;
	private String observacoes;

	@ManyToOne
	@JoinColumn(name = "id_servico", nullable = false)
	private Servico servico;

	@ManyToOne
	@JoinColumn(name = "id_ordemservico", nullable = false)
	private OrdemServico ordemServico;

	public double getValorServico() {
		this.valorServico = servico.getValor();
		return valorServico;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	

}
