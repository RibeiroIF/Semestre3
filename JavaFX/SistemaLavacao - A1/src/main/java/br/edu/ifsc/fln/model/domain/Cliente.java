package br.edu.ifsc.fln.model.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsc.fln.model.exceptions.ExceptionLavacao;

public abstract class Cliente implements IDados {

	protected int id;
	protected String nome, celular, email;
	protected LocalDate dataCadastro;
	protected List<Veiculo> listaDeVeiculos;
	protected Pontuacao pontuacao;
	
	public Cliente() {
		super();
		this.listaDeVeiculos = new ArrayList<>();
		this.pontuacao = new Pontuacao();
	}

	public Cliente(int id, String nome, String celular, String email, LocalDate dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.email = email;
		this.dataCadastro = dataCadastro;
		this.pontuacao = new Pontuacao();
		this.listaDeVeiculos = new ArrayList<>();
	}
	
	public Pontuacao getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Pontuacao pontuacao) {
		this.pontuacao = pontuacao;
	}

	public List<Veiculo> getListaDeVeiculos(){
		return listaDeVeiculos;
	}

	public void adicionarVeiculo(Veiculo veiculo) {
		listaDeVeiculos.add(veiculo);
		veiculo.setCliente(this);
	}
	
	// EXCEÇÃO AO REMOVER UM VEÍCULO QUE NÃO PERTENÇA AO CLIENTE
	public void removerVeiculo(Veiculo veiculo) throws ExceptionLavacao {
		if (!this.listaDeVeiculos.contains(veiculo)) {
			throw new ExceptionLavacao("Não é possível retirar este carro pois não pertence ao cliente");
		}
		else {
			listaDeVeiculos.remove(veiculo);
			veiculo.setCliente(null);
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + id + ", nomeCliente=" + nome + ", celular=" + celular + ", email="
				+ email + ", dataCadastro=" + dataCadastro + ", ListaDeVeiculos=" + listaDeVeiculos + ", pontuacao="
				+ pontuacao + "]";
	}

	@Override
	public String getDados() {
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append("Nome................: ").append(nome).append("\n");
		sb.append("Celular.............: ").append(celular).append("\n");
		sb.append("E-mail..............: ").append(email).append("\n");
		sb.append("Data de Cadastro....: ").append(dataCadastro.format(formatadorData)).append("\n");
		sb.append("Pontuação...........: ").append(pontuacao.verificarPontos()).append("\n");
		return sb.toString();
	}
	
	@Override
	public String getDados(String observacao) {
		StringBuilder sb = new StringBuilder();
		sb.append(getDados()).append(observacao);
		return sb.toString();
	}


}

