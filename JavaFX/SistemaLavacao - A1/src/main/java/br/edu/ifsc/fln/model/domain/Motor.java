package br.edu.ifsc.fln.model.domain;

public class Motor {

	private int potencia;
	private ETipoCombustivel tipoCombustivel;

	private Modelo modelo;
	
	public Motor() {
		super();
	}

	public Motor(int potencia, ETipoCombustivel tipoCombustivel) {
		super();
		this.potencia = potencia;
		this.tipoCombustivel = tipoCombustivel;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public ETipoCombustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(ETipoCombustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public Modelo getModelo() {
		return modelo;
	}

	@Override
	public String toString() {
		return String.valueOf(this.potencia);
	}
}
