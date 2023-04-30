package com.master.enums;

public enum FormaPagtoEnum {
	DINHEIRO("Dinheiro"),
	CARTAO_CREDITO("Cartão de Crédito"), 
	CARTAO_DEBITO("Cartão de Débito"),
	CHEQUE("Cheque"),
	BOLETO_BANCARIO("Boleto Bancário"),
	DEPOSITO_BANCARIO("Depósito Bancário");

	private String descricao;

	FormaPagtoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}


}
