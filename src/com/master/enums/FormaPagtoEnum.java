package com.master.enums;

public enum FormaPagtoEnum {
	DINHEIRO("Dinheiro"),
	CARTAO_CREDITO("Cart�o de Cr�dito"), 
	CARTAO_DEBITO("Cart�o de D�bito"),
	CHEQUE("Cheque"),
	BOLETO_BANCARIO("Boleto Banc�rio"),
	DEPOSITO_BANCARIO("Dep�sito Banc�rio");

	private String descricao;

	FormaPagtoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}


}
