package br.senai.sc.sigma.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.sigma.dao.IntervencaoDAO;
import br.senai.sc.sigma.model.Intervencao;

public class IntervencaoController {

	// ///////////////////// INSERIR //////////////////////////
	public void inserir( Intervencao intervencao ) throws SQLException, Exception {
		
		if (intervencao.getTecnico().equals("")) {
			throw new Exception("Técnico obrigatório !");

		}
		IntervencaoDAO.getInstancia().inserir( intervencao );
	}
	

	// ///////////////////// EDITAR //////////////////////////
	public void editar( Intervencao intervencao ) throws Exception {
		
		if (intervencao.getTecnico().equals("")) {
			throw new Exception("Técnico obrigatório !");

		}
		IntervencaoDAO.getInstancia().inserir( intervencao );
	}
	
	

	// ///////////////////// EXCLUIR //////////////////////////
	public void excluir( Intervencao intervencao ) throws Exception {
		if ( intervencao == null) {
			throw new Exception(" Selecione um produto para excluir ! ");
		}
	//	IntervencaoDAO.getInstancia().excluir( intervencao );
	}
	

	// ///////////////////// LISTAR INTERVENÇCÕES //////////////////////////
	public ArrayList< Intervencao > listarIntervencoes() {
		return IntervencaoDAO.getInstancia().listarIntervencoes();
	}
}
