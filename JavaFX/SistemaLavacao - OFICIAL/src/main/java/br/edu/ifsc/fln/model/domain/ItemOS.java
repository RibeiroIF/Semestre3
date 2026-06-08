package br.edu.ifsc.fln.model.domain;

public class ItemOS {

	private int id;
	private double valorServico;
	private String observacoes;
	private Servico servico;
	private OrdemServico ordemServico;

	public ItemOS() {
	}

	public ItemOS(Servico servico) {
		super();
		this.servico = servico;
	}
	
	public ItemOS(double valorServico, Servico servico) {
		super();
		this.valorServico = valorServico;
		this.servico = servico;
	}

	public ItemOS(double valorServico) {
		super();
		this.valorServico = valorServico;
	}

	public ItemOS(double valorServico, String observacoes, Servico servico) {
		super();
		this.valorServico = valorServico;
		this.observacoes = observacoes;
		this.servico = servico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorServico() {
		valorServico = this.getServico().getValor();
		return valorServico;
	}

	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}


	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	

}
