package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PessoaFisica extends Cliente {

	private String cpf;
	private LocalDate dataNascimento;
	
	public PessoaFisica() {
		super();
	}

	public PessoaFisica(int id, String nome, String celular, String email, LocalDate dataCadastro, String cpf, LocalDate dataNascimento) {
		super(id, nome, celular, email, dataCadastro);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
	}

	@Override
	public String getDados() {
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append(super.getDados());
		sb.append("CPF.................: ").append(cpf).append("\n");
		sb.append("Data de Nascimento..: ").append(dataNascimento.format(formatadorData));
		return sb.toString();
	}
	@Override
	public String getDados(String observacao) {
		StringBuilder sb = new StringBuilder();
		sb.append(getDados()).append("\n");
		sb.append("Observação..........: ").append(observacao);
		return sb.toString();
	}
	
	

}
