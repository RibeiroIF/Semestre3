package br.edu.ifsc.fln.model.report;

import br.edu.ifsc.fln.model.domain.ItemOS;
import br.edu.ifsc.fln.model.domain.OrdemServico;
import br.edu.ifsc.fln.model.exceptions.ExceptionLavacao;

import java.time.format.DateTimeFormatter;

public class ImpressaoOS {

	public String imprimirOS(OrdemServico os) throws ExceptionLavacao {
		// ESPAÇO PARA "SETAR" AS VARIÁVEIS E EXTRAS UTILIZADOS
		StringBuilder sb = new StringBuilder();
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formatText = "%-15s";
		String formatValor = "%5.2f";
		int indice = 1;
		
		// INÍCIO DO CUPOM FISCAL
		sb.append("Número:").append(os.getNumero()).append("\tDia:  ").append(os.getAgenda().format(formatadorData)).append("  status:").append(os.getStatus()).append("\n");
		sb.append("Cliente:").append(os.getVeiculo().getCliente().getNome()).append("\n");
		sb.append("Veículo:").append(os.getVeiculo().getPlaca()).append("\t\tModelo:")
		.append(os.getVeiculo().getModelo().getMotor().getPotencia()).append("/")
			.append(os.getVeiculo().getModelo().getMarca().getNome()).append("/")
				.append(os.getVeiculo().getModelo().getDescricao()).append("\n");
		sb.append("===============================================\n");
		sb.append("ITEM\t").append("DESCRICAO").append("\t\t\t VALOR").append("\n");
		sb.append("===============================================\n");
		
		//FOR LOOP PARA MOSTRAR OS ITENS DE SERVIÇO
		for (ItemOS item : os.getItens()) {
			sb.append(indice).append("\t").append(String.format(formatText, item.getServico().getDescricao())).append("\t\t\t ").append(String.format(formatValor, item.getValorServico())).append("\n");
			indice++;
		}
		
		sb.append("===============================================\n");
		sb.append("SUBTOTAL").append("\t\t\t\t ").append(os.getTotal()).append("\n");
		sb.append("DESCONTO  (").append(os.getDesconto()).append("%)\t\t\t   ").append(os.valorDesconto()).append("\n");
		sb.append("===============================================\n");
		sb.append("TOTAL").append("\t\t\t\t\t ").append(os.calcularServico());
		// FIM DO CUPOM FISCAL
		
		return sb.toString();
	}
}
