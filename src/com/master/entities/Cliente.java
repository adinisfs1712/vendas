package com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "clientes")
public class Cliente extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cliente_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "razao_social")
	private String nome;

	@Email
	private String email;
	
	@NaturalId
	@Column(name = "telefone")
	private String telefone;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	private String cnpj;

	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_ultimo_pedido")
	private Date dataUltimoPedido = new Date();

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;

	public Cliente() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataUltimoPedido() {
		return dataUltimoPedido;
	}

	public void setDataUltimoPedido(Date dataUltimoPedido) {
		this.dataUltimoPedido = dataUltimoPedido;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}




}