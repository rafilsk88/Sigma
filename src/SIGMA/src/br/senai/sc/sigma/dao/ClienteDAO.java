package br.senai.sc.sigma.dao;

import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {

	private static ClienteDAO instance;
        
        private Connection con = ConnectionUtil.getConnection();;

	public static ClienteDAO getInstancia() {
		if (instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}
        
     
	public boolean verificaExistencia(Cliente cliente) throws SQLException {
		try {                   
                    String query = "select * from CLIENTE where NOME =? AND CPF =?;";
			PreparedStatement pst = con.prepareStatement(query);

                        pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.execute();
                        ResultSet rs = pst.executeQuery();
                        if(rs.next()){
                       return true; //Encontrou o tecnico
                        }  
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
             return false;//NAO Encontrou o tecnico
	}

	public void inserir(Cliente cliente) throws SQLException {
		try {
			
						
			String query = "INSERT INTO Cliente ( NOME, CPF, CNPJ, "
					+ "ENDERECO , CIDADE, ESTADO,"
					+ "TELEFONE, EMAIL) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);

			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getCnpj());
			pst.setString(4, cliente.getEndereco());
			pst.setString(5, cliente.getCidade());
			pst.setString(6, cliente.getEstado());
			pst.setString(7, cliente.getTelefone());
			pst.setString(8, cliente.getEmail());

			pst.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Erro ao inserir cliente");// Presente apenas no
															// teste
			ex.printStackTrace();
		}
	}

	public void editar(Cliente cliente) throws SQLException {
		try {
			String query = "UPDATE cliente SET NOME = ?," + " CPF = ?,"
					+ " CNPJ = ?," + " ENDERECO = ?," + " CIDADE = ?,"
					+ " ESTADO = ?," + " TELEFONE = ?," + " EMAIL = ?"
					+ " WHERE IDCLIENTE = ?;";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getCnpj()); 
			stmt.setString(4, cliente.getEndereco());
			stmt.setString(5, cliente.getCidade());
			stmt.setString(6, cliente.getEstado());
			stmt.setString(7, cliente.getTelefone());
			stmt.setString(8, cliente.getEmail());
			stmt.setInt(9, cliente.getIdCliente());
                    
                        
                        stmt.executeUpdate();
                        
			//con.commit();

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}

	}

	public void excluir(Cliente cli) throws Exception {
		
		try {
			String query = "DELETE FROM cliente WHERE idcliente = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, cli.getIdCliente());
			stmt.executeUpdate();
							

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	
}

	public ArrayList<Cliente> listarClientes() { // Array de clientes
           ArrayList<Cliente> listaNewClientes = new ArrayList<Cliente>();
		try {
			String query = "SELECT * FROM cliente;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Cliente c = new Cliente();// Verificar
				c.setIdCliente(rs.getInt("IDCLIENTE"));
				c.setNome(rs.getString("NOME"));
				c.setCpf(rs.getString("CPF"));
				c.setCnpj(rs.getString("CNPJ"));
				c.setEndereco(rs.getString("ENDERECO"));
				c.setCidade(rs.getString("CIDADE"));
				c.setEstado(rs.getString("ESTADO"));
				c.setTelefone(rs.getString("TELEFONE"));
				c.setEmail(rs.getString("EMAIL"));

				listaNewClientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaNewClientes;
	}

}
