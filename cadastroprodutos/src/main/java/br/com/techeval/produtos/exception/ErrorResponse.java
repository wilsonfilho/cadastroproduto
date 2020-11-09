package br.com.techeval.produtos.exception;

import java.util.List;

public class ErrorResponse {
	private final int codigo;
	private final String status;
    private final String mensagem;
    private final List<ErrorObject> erros;

	public ErrorResponse(int codigo, String status, String mensagem, List<ErrorObject> erros) {
		this.codigo = codigo;
		this.status = status;
		this.mensagem = mensagem;
		this.erros = erros;
	}
	
	public String getMessage() {
		return mensagem;
	}
	public int getCode() {
		return codigo;
	}
	public String getStatus() {
		return status;
	}
	public List<ErrorObject> getErrors() {
		return erros;
	}
}
