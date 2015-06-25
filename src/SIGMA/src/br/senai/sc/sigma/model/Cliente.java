package br.senai.sc.sigma.model;

import br.senai.sc.sigma.controller.TecnicoController;

public class Cliente extends Pessoa {
	private int idCliente;
	private String cnpj;
	private String cidade;
	private String estado;
	

	public Cliente(int idCliente, String cnpj, String cidade, String estado,
			String nome, String cpf, String endereco, String telefone,
			String email) {
		super(nome, cpf, endereco, telefone, email);
		this.idCliente = idCliente;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String toString() {
		return getNome();

	}

	
	public Cliente(String nome, String cpf, String endereco, String telefone,
			String email, int idCliente) {
		super(nome, cpf, endereco, telefone, email);
		this.idCliente = idCliente;
	}

	public Cliente(String nome, String cpf, String endereco, String telefone,
			String email) {
		super(nome, cpf, endereco, telefone, email);
	}

	public Cliente() {

	}

	// cruiar um metodo to String
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
