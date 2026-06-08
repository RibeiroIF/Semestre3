package br.edu.ifsc.fln.exception;

@SuppressWarnings("serial")
public class ExceptionLavacao extends Exception {

	public ExceptionLavacao() {}
	
	public ExceptionLavacao(String mensagem) {
		super(mensagem);
	}
}
