package br.senai.sc.sigma.model;

import java.util.Date;

public class Intervencao {
	private Cliente cliente;
	private Tecnico tecnico;
	private String descricao;
	private String solucao;
	private String status;
	private Date dataInicialIntervencao;
	private Date dataFinalIntervencao;
	private String tipoAtendimento;
	private Chamado chamado;

	// Construtor para CONSULTA /* 19:06 - 20/11/2014 */
	public Intervencao(Chamado chamado, String tipoAtendimento, String status, Cliente cliente,
			Tecnico tecnico, String descricao, String solucao,
			Date dataInicialIntervencao, Date dataFinalIntervencao) {
		super();
		this.chamado = chamado;
		this.tipoAtendimento = tipoAtendimento;
		this.status = status;
		this.cliente = cliente;
		this.tecnico = tecnico;
		this.descricao = descricao;
		this.solucao = solucao;
		this.dataInicialIntervencao = dataInicialIntervencao;
		this.dataFinalIntervencao = dataFinalIntervencao;
	}

	// Construtor para INSERIR /* 19:06 - 20/11/2014 */
	public Intervencao(Chamado chamado, String tipoAtendimento, Cliente cliente, Tecnico tecnico, String descricao,
			String solucao, Date dataInicialIntervencao,
			Date dataFinalIntervencao) {
		this.chamado = chamado;
		this.tipoAtendimento = tipoAtendimento;
		this.cliente = cliente;
		this.tecnico = tecnico;
		this.descricao = descricao;
		this.solucao = solucao;
		this.dataInicialIntervencao = dataInicialIntervencao;
		this.dataFinalIntervencao = dataFinalIntervencao;
	}

	public Intervencao() {
	}

	
	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public Date getDataInicialIntervencao() {
		return dataInicialIntervencao;
	}

	public void setDataInicialIntervencao(Date dataInicialIntervencao) {
		this.dataInicialIntervencao = dataInicialIntervencao;
	}

	public Date getDataFinalIntervencao() {
		return dataFinalIntervencao;
	}

	public void setDataFinalIntervencao(Date dataFinalIntervencao) {
		this.dataFinalIntervencao = dataFinalIntervencao;
	}


}
