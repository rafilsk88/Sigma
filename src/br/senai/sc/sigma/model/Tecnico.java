package br.senai.sc.sigma.model;

public class Tecnico extends Pessoa {

	private String funcao;
	private String rg;
	private int Matricula;

	public Tecnico(String funcao, String rg, int matricula, String nome,
			String cpf, String endereco, String telefone, String email) {
		super(nome, cpf, endereco, telefone, email);
		this.funcao = funcao;
		this.rg = rg;
		this.Matricula = matricula;
	}

	public Tecnico(String nome, String cpf, String endereco, String telefone,
			String email) {
		super(nome, cpf, endereco, telefone, email);
	}

	public Tecnico() {

	}
	
	public String toString(){
		return getNome();
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public int getMatricula() {
		return Matricula;
	}

	public void setMatricula(int matricula) {
		this.Matricula = matricula;
	}

}
