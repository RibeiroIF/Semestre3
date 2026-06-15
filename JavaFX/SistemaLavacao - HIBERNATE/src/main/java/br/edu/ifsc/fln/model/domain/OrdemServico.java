package br.edu.ifsc.fln.model.domain;

import br.edu.ifsc.fln.exception.ExceptionLavacao;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private long numero;
	private double total, desconto;
	private LocalDate agenda;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private EStatus status = EStatus.ABERTA;

	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemOS> itens = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_veiculo", nullable = false)
	private Veiculo veiculo;

	public OrdemServico(){}
	
	public OrdemServico(long numero) {
		super();
		this.numero = numero;
		this.itens = new ArrayList<ItemOS>();
		this.agenda = LocalDate.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	// ESTE MÉT0DO É PARA PEGAR O TOTAL DO SERVIÇO DESCONSIDERANDO O DESCONTO
	public double getTotal() {
		calcularTotal();
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

	public void calcularTotal() {
		total = 0.0;
		for (ItemOS item : this.getItens()){
			total += item.getValorServico();
		}
		total -= (total * (desconto*0.01));
	}

	// ESSE MÉT0DO É PARA CALCULAR O TOTAL DO SERVIÇO CONSIDERANDO O DESCONTO
//	public double calcularServico() throws ExceptionLavacao {
//		if (itens.isEmpty()) {
//			throw new ExceptionLavacao("A lista de ordens está vazia, portanto não há valor!!");
//		}
//		else {
//			for (ItemOS item : this.getItens()){
//				total += item.getValorServico();
//			}
//			total -= (total * (desconto*0.01));
//		}
//		return total;
//	}
	
//	public double valorDesconto() {
//		return (total * desconto)/100;
//	}
}
