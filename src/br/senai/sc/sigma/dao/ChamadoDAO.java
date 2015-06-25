package br.senai.sc.sigma.dao;

import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.model.Intervencao;
import br.senai.sc.sigma.model.Tecnico;
import br.senai.sc.sigma.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.SizeSequence;


public class ChamadoDAO {

	private static ChamadoDAO instance;
	private ArrayList<Chamado> listaChamado = new ArrayList<Chamado>();
	private Connection con = ConnectionUtil.getConnection();

	public static ChamadoDAO getInstancia() {
		if (instance == null) {
			instance = new ChamadoDAO();
		}
		return instance;
	}

	public ChamadoDAO() {
		this.listaChamado = new ArrayList<Chamado>();
	}
	public boolean verificaExistencia(Chamado chamado) throws SQLException {
		try {
			String query = "select * from CHAMADO where TITULOCHAMADO =? AND CLIENTE =?;";
			PreparedStatement pst = con.prepareStatement(query);

			pst.setString(1, chamado.getTituloChamado());
			pst.setObject(2, chamado.getCliente());
			pst.execute();
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true; // Encontrou o tecnico
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;// NAO Encontrou o tecnico
	}

	public void inserir(Chamado chamado) throws SQLException, Exception {

		try {

			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO Chamado ( TITULOCHAMADO, DATAHORAAGENDAMENTO,"
							+ "PRIORIDADE, DESCRICAO, "
							+ "DATAHORAABERTURA) VALUES (?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pst.setString(1, chamado.getTituloChamado());
			pst.setTimestamp(2, new Timestamp(chamado.getDataAgendamento()
					.getTime()));
			pst.setString(3, chamado.getPrioridade());
			pst.setString(4, chamado.getDescricao());
			pst.setTimestamp(5,
					new java.sql.Timestamp(new java.util.Date().getTime()));

			pst.executeUpdate();

		        // recupera chave do objeto
			ResultSet rs = pst.getGeneratedKeys();

			while (rs.next()) {

			chamado.setIdChamado(rs.getInt(1));
			}

			PreparedStatement pst2 = con
					.prepareStatement("INSERT INTO CLICHAMADO (  cliente_idcliente, chamado_idchamado ) VALUES (?,?)");
			pst2.setInt(1, chamado.getCliente().getIdCliente());
			pst2.setInt(2, chamado.getIdChamado());

			pst2.executeUpdate();
                        
                        

		} catch (SQLException ex) {
			System.out.println("Erro ao inserir chamado");// Presente apenas no
															// teste
			ex.printStackTrace();
		}

	}

	public void editar(Chamado chamado) throws SQLException, Exception {

		try {
			
			String query = "UPDATE chamado SET TITULO = ?,"
					+ " DATAAGENDAMENTO = ?," + " PRIORIDADE = ?,"
					+ " DESCRICAO = ?," + " CLIENTE = ?"
					+ " WHERE IDCHAMADO = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			
			

			stmt.setString(1, chamado.getTituloChamado());
			stmt.setDate(2, new java.sql.Date(chamado.getDataAgendamento()
					.getTime()));
			stmt.setString(3, chamado.getPrioridade());
			stmt.setString(4, chamado.getDescricao());
			stmt.setObject(5, chamado.getCliente());
			stmt.setInt(6, chamado.getIdChamado());

			stmt.executeUpdate();

			con.commit();// Verificar com Andr�
			stmt.close();

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public void excluir(Chamado chamado) throws Exception {

		try {
			String query = "DELETE FROM chamado WHERE idChamado = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, chamado.getIdChamado());
			stmt.executeUpdate();
			con.commit();
			stmt.close();

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}

	}

	public ArrayList<Chamado> listarChamados() { // Array de chamados
		try {
			this.listaChamado = new ArrayList<Chamado>();
			String query = "select c.*, cli.* FROM chamado c, clichamado cc , cliente cli where c.idchamado = cc.chamado_idchamado and cli.idcliente = cc.cliente_idcliente and c.statu != 'Concluido'";
  
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Cliente cl = new Cliente();
				
				cl.setNome(rs.getString("nome"));
				cl.setCidade(rs.getString("Cidade"));
				cl.setCnpj(rs.getString("cnpj"));
				cl.setCpf(rs.getString("Cpf"));
				cl.setEmail(rs.getString("email"));
				cl.setEndereco(rs.getString("endereco"));
				cl.setEstado(rs.getString("Estado"));
				cl.setIdCliente(Integer.parseInt(rs.getString("idcliente")));
				cl.setTelefone(rs.getString("telefone"));

				Chamado c = new Chamado();

				c.setIdChamado(rs.getInt("idChamado"));
				c.setPrioridade(rs.getString("PRIORIDADE"));
				c.setDataAbertura(new java.util.Date(rs.getTimestamp("dataHoraAbertura").getTime()));
				c.setCliente(cl);
				c.setTituloChamado(rs.getString("TITULOCHAMADO"));
				c.setDataAgendamento(new java.util.Date(rs.getTimestamp("dataHoraAgendamento").getTime()));
				c.setStatu(rs.getString("statu"));
				c.setDescricao(rs.getString("descricao"));

							

				this.listaChamado.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.listaChamado;

	}
}
