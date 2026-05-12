package report;

import java.time.format.DateTimeFormatter;

import domain.Cliente;
import domain.PessoaFisica;
import domain.Veiculo;

public class Relatorio {

	@SuppressWarnings("unused")
	public String imprimir(Cliente cliente) {
		// ESPAÇO PARA "SETAR" AS VARIÁVEIS E EXTRAS UTILIZADOS
		StringBuilder sb = new StringBuilder();
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//String formatText = "%-15s";
		int quantidadeVeiculos = 0;
		
		// INÍCIO DO RELATÓRIO
		// ESPAÇO PARA DEFINIR CLIENTE PF E PJ
		if (cliente instanceof PessoaFisica) {
			sb.append("\t      DADOS DO CLIENTE PF").append("\n");
			sb.append("=============================================\n");
			sb.append("\t\t    ID:").append(cliente.getId()).append("\n");
			sb.append("=============================================\n");
		}
		else {
			sb.append("\t\t    DADOS DO CLIENTE PJ").append("\n");
			sb.append("\t\t    ID:").append(cliente.getId()).append("\n");
		}
		
		sb.append("Nome:").append("\t\t\t\t       ").append(cliente.getNome()).append("\n");
		sb.append("Celular:").append("\t\t\t   ").append(cliente.getCelular()).append("\n");
		sb.append("E-mail: ").append("\t\t     ").append(cliente.getEmail()).append("\n");
		sb.append("Data de Cadastro:").append("\t\t   ").append(cliente.getDataCadastro().format(formatadorData)).append("\n");
		sb.append("==============================================\n");
		sb.append("\t    VEÍCULOS DO CLIENTE").append("\n");
		sb.append("----------------------------------------------\n");
		sb.append("MARCA\t\t").append("MODELO").append("\t\t\tPLACA").append("\n");
		sb.append("==============================================\n");
		
		// FOR LOOP PARA PEGAR AS INFOS DE CADA VEÍCULO
		for (Veiculo veiculos : cliente.getListaDeVeiculos()) {
			sb.append(veiculos.getModelo().getMarca().getNome()).append("\t\t")
					.append(veiculos.getModelo().getDescricao()).append("\t\t     ")
						.append(veiculos.getPlaca()).append("\n");
		}
		
		
		sb.append("==============================================\n");
		sb.append("Pontuação:").append("\t\t\t\t   ").append(cliente.getPontuacao().verificarPontos());
		// FIM DO RELATÓRIO
		return sb.toString();
	}
}
