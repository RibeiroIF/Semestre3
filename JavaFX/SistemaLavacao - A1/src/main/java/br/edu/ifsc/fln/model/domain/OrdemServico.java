package br.edu.ifsc.fln.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.fln.model.exceptions.ExceptionLavacao;

public class OrdemServico {

	private long numero;
	private double total, desconto;
	private LocalDate agenda;
	private EStatus status = EStatus.ABERTA;
	private Veiculo veiculo; 
	private List<ItemOS> itens;
	
	public OrdemServico(long numero) {
		super();
		this.numero = numero;
		this.itens = new ArrayList<ItemOS>();
		this.agenda = LocalDate.now();
	}
	
	public List<ItemOS> getItens() {
		return itens;
	}

	public void setItens(List<ItemOS> itens) {
		this.itens = itens;
	}

	
	public long getNumero() {
		return numero;
	}
	
	public void setNumero(long numero) {
		this.numero = numero;
	}
	
	// ESTE MÉTODO É PARA PEGAR O TOTAL DO SERVIÇO DESCONSIDERANDO O DESCONTO
	public double getTotal() {
		for (ItemOS item : itens) {
			this.total += item.getValorServico();
		}
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getDesconto() {
		return desconto;
	}
	
	public void setDesconto(double desconto) throws ExceptionLavacao {
		if (desconto < 0 || desconto > 100) {
			throw new ExceptionLavacao("O valor do desconto não é viável!!");
		}
		else {
			this.desconto = desconto;
		}
	}
	
	public LocalDate getAgenda() {
		return agenda;
	}
	
	public void setAgenda(LocalDate agenda) {
		this.agenda = agenda;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	
	public void adicionarItem(ItemOS item) throws ExceptionLavacao {
		if (this.status != EStatus.ABERTA) {
			throw new ExceptionLavacao("Não é possível adicionar a ordem pois não está aberta");
		}
		else {
			this.itens.add(item);
			this.setVeiculo(veiculo);
			this.getVeiculo().getCliente().getPontuacao().somarPontos(Servico.getPontos());
			item.setOrdemServico(this);
		}
	}
	
	public void removerItem(ItemOS item) throws ExceptionLavacao {
		if (this.status != EStatus.ABERTA) {
			throw new ExceptionLavacao("A ordem já não está mais em circulação, portanto a ação é inválida");
		}
		else {
			this.itens.add(item);
			this.setVeiculo(null);
			this.getVeiculo().getCliente().getPontuacao().subtrairPontos(Servico.getPontos());
			item.setOrdemServico(null);
		}
	}
	
	// ESSE MÉTODO É PARA CALCULAR O TOTAL DO SERVIÇO CONSIDERANDO O DESCONTO
	public double calcularServico() throws ExceptionLavacao {
		if (itens.isEmpty()) {
			throw new ExceptionLavacao("A lista de ordens está vazia, portanto não há valor!!");
		}
		else {
			total -= (total * (desconto*0.01));
		}
		return total;
	}
	
	public double valorDesconto() {
		return (total * desconto)/100;
	}
}
