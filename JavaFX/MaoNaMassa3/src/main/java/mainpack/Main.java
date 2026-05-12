package mainpack;

import java.time.LocalDate;

import domain.Cliente;
import domain.Cor;
import domain.ECategoria;
import domain.ETipoCombustivel;
import domain.ItemOS;
import domain.Marca;
import domain.Modelo;
import domain.OrdemServico;
import domain.PessoaFisica;
import domain.Servico;
import domain.Veiculo;
import exceptions.ExceptionLavacao;
import report.ImpressaoOS;
import report.Relatorio;

public class Main {

	public static void main(String[] args) {
		
		//INSTANCIANDO SERVIÇOS
		Servico servico1 = new Servico(1, "Lavação interna", 90);
		Servico servico2 = new Servico(1, "Lavação externa", 80);
		Servico servico3 = new Servico(1, "Lavação cera", 70);
		
		//INSTANCIANDO INFORMAÇÕES DO VEÍCULO
		//INSTANCIANDO CORES
		Cor cor1 = new Cor("Preto");
		Cor cor2 = new Cor("Branco");
		Cor cor3 = new Cor("Azul");
		
		//INSTANCIANDO MARCAS
		Marca marca1 = new Marca(1, "RAM");
		Marca marca2 = new Marca(2, "BYD");
		
		//INSTANCIANDO MODELOS
		Modelo modelo1 = new Modelo(11, "SUV", marca1, ECategoria.GRANDE, 220, ETipoCombustivel.GASOLINA);
		Modelo modelo2 = new Modelo(22, "Sedan", marca2, ECategoria.PADRÃO, 170, ETipoCombustivel.FLEX);
		
		//INSTANCIANDO VEÍCULO
		Veiculo veiculo1 = new Veiculo("XLR8-222", modelo1);
		Veiculo veiculo2 = new Veiculo("ABCD-555", modelo1);
		Veiculo veiculo3 = new Veiculo(1, "XYZB-111", "Dinâmico", modelo2, cor3);
		
		veiculo1.setCor(cor1);
		veiculo2.setCor(cor2);
		
		//INSTANCIANDO INFORMAÇÕES DO CLIENTE
		Cliente cliente1 = null;
		cliente1 = new PessoaFisica(111, "Marcos", "99999-9999", "Gremio@gmail.com", LocalDate.of(2025, 11, 28), "123456789-10", LocalDate.of(1970, 10, 10));
		cliente1.adicionarVeiculo(veiculo1);
		cliente1.adicionarVeiculo(veiculo2);
		cliente1.adicionarVeiculo(veiculo3);
		
		//INSTANCIANDO ORDEM
		OrdemServico ordem1 = new OrdemServico(1);
		ordem1.setVeiculo(veiculo1);
		try {
			ordem1.adicionarItem(new ItemOS(90, servico1));
			ordem1.adicionarItem(new ItemOS(80, servico2));
			ordem1.adicionarItem(new ItemOS(70, servico3));
			System.out.println("\t\t CUPOM FISCAL ABAIXO");
			System.out.println("-------------------------------------------------------");
			printOS(ordem1);
		} catch (ExceptionLavacao e) {
			System.out.println("Erro: " +e.getMessage());
		}
		
		//FAZENDO O PRINT PARA CONFIRMAR A FUNCIONALIDADE DO PROGRAMA
		System.out.println("................................................");
		System.out.println();
		System.out.println();
		System.out.println("\t RELATÓRIO DO CLIENTE ABAIXO");
		System.out.println("-------------------------------------------------------");
		printCliente(cliente1);
		System.out.println("..............................................");
		

	}
	
	public static void printOS(OrdemServico os) throws ExceptionLavacao {
		ImpressaoOS impressao = new ImpressaoOS();
		System.out.println(impressao.imprimirOS(os));
	}
	
	public static void printCliente(Cliente cliente) {
		Relatorio relatorio = new Relatorio();
		System.out.println(relatorio.imprimir(cliente));
	}
	
	
}
