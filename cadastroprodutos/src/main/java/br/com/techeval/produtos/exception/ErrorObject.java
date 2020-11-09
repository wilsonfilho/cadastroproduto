package br.com.techeval.produtos.exception;

public class ErrorObject {
	
	private final String mensagem;
    private final String field;
    private final Object parametros;
    
	public ErrorObject(String message, String field, Object parameter) {
		this.mensagem = message;
		this.field = field;
		this.parametros = parameter;
	}
	
	public String getMessage() {
		return mensagem;
	}
	public String getField() {
		return field;
	}
	public Object getParameter() {
		return parametros;
	}
}
