package br.senai.sc.sigma.controller;

import java.util.ArrayList;

import br.senai.sc.sigma.dao.ClienteDAO;
import br.senai.sc.sigma.dao.ConsultaDAO;import br.senai.sc.sigma.model.Chamado;

import br.senai.sc.sigma.dao.TecnicoDAO;
import br.senai.sc.sigma.model.Tecnico;

public class TecnicoController {
	// ///////////////////// INSERIR //////////////////////////
	public void inserir(Tecnico tecnico) throws Exception {
		//  FUNCAO, nome, cpf
		if (tecnico.getFuncao().equals("")) {
			throw new Exception("FUN��O Obrigatoria!");
		}
		if (tecnico.getNome().equals("")) {
			throw new Exception("NOME Obrigatorio!");
		}
		if (tecnico.getCpf().equals("")) {
			throw new Exception("CPF Obrigatorio!");
		}

		TecnicoDAO dao = new TecnicoDAO();
		dao.inserir( tecnico );
	}

	// ///////////////////// EDITAR //////////////////////////
	public void editar(Tecnico tecnico) throws Exception {

		//  FUNCAO, nome, cpf
		if (tecnico.getFuncao().equals("")) {
			throw new Exception("FUN��O Obrigatoria!");
		}
		if (tecnico.getNome().equals("")) {
			throw new Exception("NOME Obrigatorio!");
		}
		
		TecnicoDAO dao = new TecnicoDAO();
		dao.editar( tecnico );
		
	}

	// ///////////////////// EXCLUIR //////////////////////////
	public void excluir( Integer matricula ) throws Exception {
		if ( matricula == null) {
			throw new Exception("Selecione um t�cnico !");
		}
		TecnicoDAO.getInstancia().excluir(matricula);
		
	}

	// ///////////////////// LISTAR TECNICOS //////////////////////////
	public ArrayList<Tecnico> listaTecnicos() {
		return TecnicoDAO.getInstancia().listarTecnicos();
	}
       
}
