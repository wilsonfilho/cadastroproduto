package br.com.techeval.produtos.exception;

import java.util.Date;

public class ErrorMessage {
	private final int codigo;
	private final String status;
	private final String erro;
	private final Date datetime;
	private final String buildMessage;
	
	public ErrorMessage(int codigo, String status, String erro, Date datetime, String buildMessage) {
		this.codigo = codigo;
		this.status = status;
		this.erro = erro;
		this.datetime = datetime;
		this.buildMessage = buildMessage;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the erro
	 */
	public String getErro() {
		return erro;
	}

	/**
	 * @return the datetime
	 */
	public Date getDatetime() {
		return datetime;
	}

	/**
	 * @return the buildMessage
	 */
	public String getBuildMessage() {
		return buildMessage;
	}
	
	
	
	
}
