package br.senai.sc.sigma.controller;

import br.senai.sc.sigma.dao.ChamadoDAO;
import br.senai.sc.sigma.dao.ConsultaDAO;
import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.model.Intervencao;
import br.senai.sc.sigma.model.Tecnico;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class ConsultaController {


	
	public void excluir( Chamado chamado ) throws Exception {
		if ( chamado == null) {
			throw new Exception(" Selecione um produto para excluir ! ");
		}
		ChamadoDAO.getInstancia().excluir( chamado );
	}

	// ///////////////////// LISTAR CHAMADOS //////////////////////////
	
	public ArrayList< Chamado > listarChamadosPorCliente(Cliente cli) {
		return ConsultaDAO.getInstancia().listarConsultaCliente(cli);
	}
        
        public ArrayList< Chamado > listarChamadosPorTecnico(Tecnico tec) {
		return ConsultaDAO.getInstancia().listarConsultaTecnico(tec);
	}
        
        public ArrayList< Chamado > listarChamadosPorChamado(Chamado cham) {
		return ConsultaDAO.getInstancia().listarConsultaChamado(cham);
	}
        
        public ArrayList< Tecnico > listarTecnicoChamado() {
                 return ConsultaDAO.getInstancia().listarTecnicosChamado();
    }


}
