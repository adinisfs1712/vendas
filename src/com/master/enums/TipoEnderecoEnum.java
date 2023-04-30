package com.master.enums;

public enum TipoEnderecoEnum {

	com("Comercial"), res("Residencial"), cob("Cobran�a"), ent("Entrega");
	
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	private TipoEnderecoEnum(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return getTipo();

	}

}
