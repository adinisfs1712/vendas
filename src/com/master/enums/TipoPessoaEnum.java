package com.master.enums;

public enum TipoPessoaEnum {

    F("F�sica"), J("Jur�dica");

    private String pessoa;

    private TipoPessoaEnum(String pessoa) {
	this.pessoa = pessoa;
    }

    public String toString() {
	return this.pessoa;
    }

    public String getPessoa() {
	return pessoa;
    }

}