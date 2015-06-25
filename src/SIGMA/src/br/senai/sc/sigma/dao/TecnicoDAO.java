package br.senai.sc.sigma.dao;


import br.senai.sc.sigma.model.Tecnico;
import br.senai.sc.sigma.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TecnicoDAO {
	private static TecnicoDAO instance;
	
	private Connection con = ConnectionUtil.getConnection();;

	public static TecnicoDAO getInstancia() {
		if (instance == null) {
			instance = new TecnicoDAO();
		}
		return instance;
	}
        
        public boolean verificaExistencia(Tecnico tecnico) throws SQLException {
		try {                   
                    String query = "select * from TECNICO where NOME =? AND CPF =?";
			PreparedStatement pst = con.prepareStatement(query);

                        pst.setString(1, tecnico.getNome());
			pst.setString(2, tecnico.getCpf());
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
	

	public void inserir(Tecnico tecnico) throws SQLException {
		try {
			String query = "INSERT INTO TECNICO ( FUNCAO, NOME, CPF, RG, "
					+ "ENDERECO, TELEFONE, EMAIL) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);

			pst.setString(1, tecnico.getFuncao());
			pst.setString(2, tecnico.getNome());
			pst.setString(3, tecnico.getCpf());
			pst.setString(4, tecnico.getRg());
			pst.setString(5, tecnico.getEndereco());
			pst.setString(6, tecnico.getTelefone());
			pst.setString(7, tecnico.getEmail());

			pst.execute();

		} catch (SQLException ex) {

			ex.printStackTrace();
		}
	}

	public void editar(Tecnico tecnico) throws SQLException {
            
          
		try {
			String query = "UPDATE tecnico SET  FUNCAO = ?," + " NOME = ?,"
					+ " CPF = ?," + " RG = ?," + " ENDERECO = ?,"
					+ " TELEFONE = ?," + " EMAIL = ?" + " WHERE MATRICULA = ?;";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, tecnico.getFuncao());
			stmt.setString(2, tecnico.getNome());
			stmt.setString(3, tecnico.getCpf());
			stmt.setString(4, tecnico.getRg());
			stmt.setString(5, tecnico.getEndereco());
			stmt.setString(6, tecnico.getTelefone());
			stmt.setString(7, tecnico.getEmail());
			stmt.setInt(8, tecnico.getMatricula());

			stmt.executeUpdate();

			

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}

	}

	public void excluir( Integer matricula ) throws Exception {
		try {
			String query = "DELETE from TECNICO where matricula = ?";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setInt(1, matricula );

			stmt.executeUpdate();

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}

	}

	public ArrayList<Tecnico> listarTecnicos() { // Array de tecnicos
                 ArrayList<Tecnico>listaNewTecnicos = new ArrayList<Tecnico>();
		try {
			String query = "SELECT * FROM TECNICO";
		        Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Tecnico tc = new Tecnico();// verificar
				tc.setMatricula(rs.getInt("MATRICULA"));  
				tc.setFuncao(rs.getString("FUNCAO"));
				tc.setNome(rs.getString("NOME"));
				tc.setCpf(rs.getString("CPF"));
				tc.setRg(rs.getString("RG"));
				tc.setEndereco(rs.getString("ENDERECO"));
				tc.setTelefone(rs.getString("TELEFONE"));
				tc.setEmail(rs.getString("EMAIL"));

				listaNewTecnicos.add(tc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaNewTecnicos;
	}

}
