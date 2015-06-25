package br.senai.sc.sigma.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Chamado {

	private String tituloChamado;
	private Date dataAgendamento;
	private Date dataAbertura;
	private String prioridade;
	private String descricao;
	private Cliente cliente;
	private Integer idChamado;
	private String statu;
	

	private ArrayList<Intervencao> listaIntervencoes;

	/* CONSTRUTOR PARA CONSULTA */
	
	public Chamado(String statu, String tituloChamado, Date dataAgendamento,
			String prioridade, String descricao, Cliente cliente,
			Integer idChamado, ArrayList<Intervencao> listaIntervencoes) {
		this.statu = statu;
		this.tituloChamado = tituloChamado;
		this.dataAgendamento = dataAgendamento;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.cliente = cliente;
		this.idChamado = idChamado;
		this.listaIntervencoes = listaIntervencoes;
	}

	// //// CONSTRUTOR PARA INSERIR \\\\\\\

	public Chamado( String tituloChamado,
			Date dataAgendamento, String prioridade, String descricao,
			Cliente cliente) {
		this.tituloChamado = tituloChamado;
		this.dataAgendamento = dataAgendamento;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.cliente = cliente;
	}

	public Chamado() {
	}

		
	
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getTituloChamado() {
		return tituloChamado;
	}

	public void setTituloChamado(String tituloChamado) {
		this.tituloChamado = tituloChamado;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(Integer idChamado) {
		this.idChamado = idChamado;
	}

	public ArrayList<Intervencao> getListaIntervencoes() {
		return listaIntervencoes;
	}

	public void setListaIntervencoes(ArrayList<Intervencao> listaIntervencoes) {
		this.listaIntervencoes = listaIntervencoes;
	}

}
