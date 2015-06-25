package br.senai.sc.sigma.controller;

import br.senai.sc.sigma.dao.ClienteDAO;
import br.senai.sc.sigma.model.Cliente;
import java.util.ArrayList;

public class ClienteController {

	// ///////////////////// INSERIR //////////////////////////
	public void inserir(Cliente cliente) throws Exception {
		// nome, cpf, endereco, telefone, email, cnpj, cidade, estado

		if ( cliente.getNome().equals("") ) {
			throw new Exception("NOME Obrigatorio!");
		}
		
		if ( cliente.getCpf().equals("")  ) {			
			if ( cliente.getCnpj().equals("")  ) {
				throw new Exception("CPF ou CNPJ Obrigatorio!");
			}
		}

		ClienteDAO dao = new ClienteDAO();

		dao.inserir(cliente);
	}

	// ///////////////////// EDITAR //////////////////////////
	public void editar(Cliente cliente) throws Exception {

		if (cliente.getNome().equals("")) {
			throw new Exception("NOME Obrigatorio!");
		}

		
		ClienteDAO.getInstancia().editar(cliente);
	}

	// ///////////////////// EXCLUIR //////////////////////////
	public void excluir(Cliente cli) throws Exception {
		if (cli == null) {
			throw new Exception("Selecione um cliente !");
		}
		ClienteDAO.getInstancia().excluir(cli);
	}

	// ///////////////////// LISTAR CLIENTES //////////////////////////
	public ArrayList<Cliente> listaClientes() {
		return ClienteDAO.getInstancia().listarClientes();
	}

}
