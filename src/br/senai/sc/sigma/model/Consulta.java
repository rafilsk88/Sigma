
package br.senai.sc.sigma.model;

import java.util.Date;


public class Consulta {
    private Cliente cliente;
    private Tecnico tecnico;
    private  Date dataInicial;
    private  Date dataFinal;
    private Chamado chamado;

    public Consulta(Cliente cliente, Tecnico tecnico, Date dataInicial, Date dataFinal, Chamado chamado) {
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.chamado = chamado;
    }

    public Consulta() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }
    
    
}
