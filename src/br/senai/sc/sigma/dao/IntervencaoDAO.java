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
import java.util.ArrayList;

import javax.swing.JLabel;

public class IntervencaoDAO {

    private static IntervencaoDAO instance;
    private ArrayList<Intervencao> listaIntervencao = new ArrayList<Intervencao>();
    private Connection con = ConnectionUtil.getConnection();

    public static IntervencaoDAO getInstancia() {
        if (instance == null) {
            instance = new IntervencaoDAO();
        }
        return instance;
    }

    public IntervencaoDAO() {
        this.listaIntervencao = new ArrayList<Intervencao>();
    }

    public boolean verificaExistencia(Intervencao intervencao)
            throws SQLException {
        try {
            String query = "select * from intervencoes where chamado_idchamado = ? AND tecnico_matricula = ?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setObject(1, intervencao.getChamado().getIdChamado());
            pst.setObject(2, intervencao.getTecnico().getMatricula());
            pst.execute();
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void inserir(Intervencao intervencao) throws SQLException {
        try {

            String query2 = "UPDATE chamado SET STATU = ?"
                    + " WHERE IDCHAMADO = ?;";

            PreparedStatement pst2 = con.prepareStatement(query2, PreparedStatement.RETURN_GENERATED_KEYS);

            pst2.setString(1, intervencao.getStatus());
            pst2.setInt(2, intervencao.getChamado().getIdChamado());

            pst2.execute();

            ResultSet rs = pst2.getGeneratedKeys();

            while (rs.next()) {

                intervencao.getChamado().setIdChamado(rs.getInt(1));
            }
            String query = "INSERT INTO Intervencoes ( chamado_idChamado, tecnico_matricula,istatu, solucao,"
                    + "DataHoraInicialIntervencao, dataHoraFinalIntervencao, tipoAtendimento ) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, intervencao.getChamado().getIdChamado());
            pst.setInt(2, intervencao.getTecnico().getMatricula());
            pst.setString(3, intervencao.getStatus());
            pst.setString(4, intervencao.getSolucao());
            pst.setTimestamp(5, new Timestamp(intervencao
                    .getDataInicialIntervencao().getTime()));
            pst.setTimestamp(6, new Timestamp(intervencao
                    .getDataFinalIntervencao().getTime()));
            pst.setString(7, intervencao.getTipoAtendimento());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir intervencao");

            ex.printStackTrace();
        }
    }

    public void editar(Intervencao intervencao) throws SQLException {
        try {
            String query = "UPDATE intervencao SET CLIENTE = ?,"
                    + " DESCRICAO = ?,"
                    + " SOLUCAO = ?,"
                    + " DATAINICIALINTERVENCAO = ?,"
                    + " DATAFINALINTERVENCAO = ? where chamado_idchamado = ? and tecnico_matricula ?;";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, intervencao.getCliente().getNome());
            stmt.setString(2, intervencao.getDescricao());
            stmt.setString(3, intervencao.getSolucao());
            stmt.setDate(4, new java.sql.Date(intervencao
                    .getDataInicialIntervencao().getTime()));
            stmt.setDate(5, new java.sql.Date(intervencao
                    .getDataFinalIntervencao().getTime()));
			// stmt.setObject(6, intervencao.getCliente());
            // stmt.setString(7, intervencao.getDescricao());
            stmt.executeUpdate();

            con.commit();

        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }
    }

    /*public void excluir(Intervencao intervencao) throws Exception {

     try {
     String query = "DELETE FROM intervencao WHERE idIntervencao = ?;";// Verificar
     PreparedStatement stmt = con.prepareStatement(query);
     stmt.setInt(1, i);
     stmt.executeUpdate();
     con.commit();

     } catch (SQLException e) {
     con.rollback();
     e.printStackTrace();
     }

     }   */
    public ArrayList<Intervencao> listarIntervencoes() {
        try {
            this.listaIntervencao = new ArrayList<Intervencao>();
            String query = "SELECT * FROM intervencoes";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                Intervencao it = new Intervencao();
                it.setStatus(rs.getString("istatu"));
                it.setSolucao(rs.getString("SOLUCAO"));
                it.setDataInicialIntervencao(rs
                        .getDate("DATAhoraINICIALINTERVENCAO"));
                it.setDataInicialIntervencao(rs
                        .getDate("DATAhoraFINALINTERVENCAO"));
                it.setTipoAtendimento(rs.getString("tipoAtendimento"));

                Chamado ch = new Chamado();
                ch.setIdChamado(rs.getInt("chamado_idchamado"));

                Tecnico tec = new Tecnico();
                tec.setMatricula(rs.getInt("tecnico_matricula"));

                this.listaIntervencao.add(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaIntervencao;
    }

}
