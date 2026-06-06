package br.edu.ifsc.fln.model.domain;

public class Modelo {

	private int id;
	private String descricao;
	private Marca marca;
	private ECategoria categoria;
	private Motor motor;
	private Veiculo veiculo;
	
	public Modelo() {
		this.motor = new Motor();
	}
	
	public Modelo(int id, String descricao, Marca marca, ECategoria categoria, int potencia, ETipoCombustivel combustivel) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.marca = marca;
		this.categoria = categoria;
		this.motor = new Motor(potencia, combustivel);
	}
	
	public Motor getMotor() {
		return motor;
	}

	public ECategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(ECategoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Veiculo getVeiculo() {
		return veiculo;
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

	@Override
	public String toString() {
		return this.descricao;
	}
}
