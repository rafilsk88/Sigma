package br.senai.sc.sigma.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.sigma.dao.ChamadoDAO;
import br.senai.sc.sigma.model.Chamado;

public class AbrirChamadoController {

	// ///////////////////// INSERIR //////////////////////////
	
	public void inserir( Chamado chamado ) throws SQLException, Exception {
		if (chamado.getDataAgendamento().equals("")) {
			throw new Exception("Data de agendamento obrigat�rio !");

		} if (chamado.getCliente() == null) {
			throw new Exception( "Cliente obrigat�rio !");

		} if (chamado.getDescricao().equals("")) {
			throw new Exception("Descri��o do chamado  obrigat�rio !");
			
		} if (chamado.getPrioridade().equals("")) {
			throw new Exception( "Prioridade do chamado obrigat�rio !");

		}
		ChamadoDAO.getInstancia().inserir( chamado );
	}
	
	
	// ///////////////////// EDITAR //////////////////////////
	
	public void editar( Chamado chamado ) throws Exception {

		if (chamado.getDataAgendamento().equals("")) {
			throw new Exception("Data de agendamento obrigat�rio !");

		} if (chamado.getCliente() == null) {
			throw new Exception( "Cliente obrigat�rio !");

		} if (chamado.getDescricao().equals("")) {
			throw new Exception("Descri��o do chamado  obrigat�rio !");
			
		} if (chamado.getPrioridade().equals("")) {
			throw new Exception( "Prioridade do chamado obrigat�rio !");

		}
		ChamadoDAO.getInstancia().editar( chamado );
	}

	// ///////////////////// EXCLUIR //////////////////////////
	
	public void excluir( Chamado chamado ) throws Exception {
		if ( chamado == null) {
			throw new Exception(" Selecione um produto para excluir ! ");
		}
		ChamadoDAO.getInstancia().excluir( chamado );
	}

	// ///////////////////// LISTAR CHAMADOS //////////////////////////
	
	public ArrayList< Chamado > listarChamados() {
		return ChamadoDAO.getInstancia().listarChamados();
	}
        

}
