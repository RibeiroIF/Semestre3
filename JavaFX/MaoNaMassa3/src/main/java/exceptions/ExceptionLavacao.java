package exceptions;

@SuppressWarnings("serial")
public class ExceptionLavacao extends Exception {

	public ExceptionLavacao() {}
	
	public ExceptionLavacao(String mensagem) {
		super(mensagem);
	}
}
