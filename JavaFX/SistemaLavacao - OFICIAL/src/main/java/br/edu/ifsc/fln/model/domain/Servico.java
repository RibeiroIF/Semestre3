package br.edu.ifsc.fln.model.domain;

public class Servico {

	private int id;
	private String descricao;
	private double valor;
	static int pontos = 0;
	private ECategoria categoria;
	
	public Servico() {
		super();
	}

	public Servico(double valor) {
		super();
		this.valor = valor;
	}

	public Servico(int id, String descricao, double valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ECategoria getCategoria() {
		return categoria;
	}

	public static int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
}
