

package br.senai.sc.sigma.dao;


import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.model.Consulta;
import br.senai.sc.sigma.model.Intervencao;
import br.senai.sc.sigma.model.Tecnico;
import br.senai.sc.sigma.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class ConsultaDAO {
    private static ConsultaDAO instance;
    private ArrayList<Chamado> listaConsulta = new ArrayList<Chamado>();
    private ArrayList<Tecnico> listaConsultaTec = new ArrayList<Tecnico>();
    private Connection con = ConnectionUtil.getConnection();
    Chamado cham = new Chamado();

    public static ConsultaDAO getInstancia() {
        if (instance == null) {
            instance = new ConsultaDAO();
        }
        return instance;
    }
    

        
    public ArrayList<Chamado> listarConsultaCliente(Cliente cli) { //
        try {
            
            this.listaConsulta = new ArrayList<Chamado>();
            String query = "SELECT ch.* FROM clichamado cc, cliente c, chamado ch" +
            		" WHERE c.idcliente = cc.cliente_idcliente and  ch.idchamado = cc.chamado_idchamado and c.idcliente =?;";
            
           
            Statement stmt = con.createStatement();
           
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cli.getIdCliente());
            ResultSet rs =  pst.executeQuery();

            
           while (rs.next()) {
            	
              Chamado c = new Chamado();
               c.setIdChamado(rs.getInt("idChamado"));
				c.setPrioridade(rs.getString("PRIORIDADE"));
				c.setDataAbertura(rs.getDate("dataHoraAbertura"));
				c.setTituloChamado(rs.getString("TITULOCHAMADO"));
				c.setDataAgendamento(rs.getDate("dataHoraAgendamento"));
                                c.setStatu(rs.getString("statu"));


				this.listaConsulta.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.listaConsulta;
    }
    public ArrayList<Chamado> listarConsultaChamado(Chamado cha) { //
        try {
            
            this.listaConsulta = new ArrayList<Chamado>();
            String query = "SELECT ch.* FROM clichamado cc, cliente c, chamado ch" +
            		" WHERE c.idcliente = cc.cliente_idcliente and  ch.idchamado = cc.chamado_idchamado and ch.idchamado =?;";
            
           
            Statement stmt = con.createStatement();
           
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cha.getIdChamado());
            ResultSet rs =  pst.executeQuery();

            
           while (rs.next()) {
            	
              Chamado c = new Chamado();
               c.setIdChamado(rs.getInt("idChamado"));
				c.setPrioridade(rs.getString("PRIORIDADE"));
                c.setDescricao(rs.getString("Descricao"));
                c.setDataAbertura(rs.getDate("dataHoraAbertura"));
				c.setTituloChamado(rs.getString("TITULOCHAMADO"));
				c.setDataAgendamento(rs.getDate("dataHoraAgendamento"));
                c.setStatu(rs.getString("statu"));


				this.listaConsulta.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.listaConsulta;
    }
    public ArrayList<Chamado> listarConsultaTecnico(Tecnico tec) {  //Realizar alterações
        try {
            
            this.listaConsulta = new ArrayList<Chamado>();
            String query = "SELECT  t.*,  cha.*, i.* from  clichamado cc, chamado cha, cliente cli, intervencoes i, tecnico t  "
                    + "where cli.idcliente = cc.cliente_idcliente and cha.idchamado = cc.chamado_idchamado "
                    + "and cha.idchamado = i.chamado_idchamado and i.tecnico_matricula = t.matricula and  t.matricula = ? ;";
                    
                  

            Statement stmt = con.createStatement();           
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, tec.getMatricula());
            ResultSet rs =  pst.executeQuery();
            
            while (rs.next()) {
                Chamado c = new Chamado();
                c.setIdChamado(rs.getInt("idChamado"));
				c.setPrioridade(rs.getString("PRIORIDADE"));
				c.setDataAbertura(rs.getDate("dataHoraAbertura"));
				// c.getCliente().setNome(rs.getString("CLIENTE"));
				c.setTituloChamado(rs.getString("TITULOCHAMADO"));
				c.setDataAgendamento(rs.getDate("dataHoraAgendamento"));
                                c.setStatu(rs.getString("statu"));
				// c.setListaIntervencoes((ArrayList<Intervencao>)
				// rs.getArray("Status"));

				// /c.s((rs.getString("STATUS")); Alterar dados na interven��o
                this.listaConsulta.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.listaConsulta;
    }
    
  
    
    public ArrayList<Chamado> listarConsultaPeriodo(Chamado cha) { //Realizar alterações
        try {
            
           // this.listaConsulta = new ArrayList<Consulta>();
            String query = "SELECT c.nome, t.nome, cm.titulochamado, cm.dataabertura, cm.dataconclusao\n" +
                    "  from tecnico t, cliente c, chamado cm " +
                   "  where cm.dataAgendamento =?;";
            
            Statement stmt = con.createStatement();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            pst.setObject(1, cha.getDataAgendamento());//  

            while (rs.next()) {
            	 Chamado c = new Chamado();
                 c.setIdChamado(rs.getInt("idChamado"));
 				c.setPrioridade(rs.getString("PRIORIDADE"));
 				c.setDataAbertura(rs.getDate("dataHoraAbertura"));
 				// c.getCliente().setNome(rs.getString("CLIENTE"));
 				c.setTituloChamado(rs.getString("TITULOCHAMADO"));
 				c.setDataAgendamento(rs.getDate("dataHoraAgendamento"));
                                c.setStatu(rs.getString("statu"));
 				// c.setListaIntervencoes((ArrayList<Intervencao>)
 				// rs.getArray("Status"));

 				// /c.s((rs.getString("STATUS")); Alterar dados na interven��o

                this.listaConsulta.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return this.listaConsulta;
    
     }
    public ArrayList<Tecnico> listarTecnicosChamado() {  
        try {
            
            this.listaConsultaTec = new ArrayList<Tecnico>();
            String query = "SELECT  t.*, cha.*  from  clichamado cc, chamado cha, cliente cli, intervencoes i, tecnico t "
                    + "where cli.idcliente = cc.cliente_idcliente and cha.idchamado = cc.chamado_idchamado "
                    + "and cha.idchamado = i.chamado_idchamado and i.tecnico_matricula = t.matricula and cha.idchamado =109;";

            Statement stmt = con.createStatement();
            
           PreparedStatement pst = con.prepareStatement(query);
           //pst.setInt(1, cham.getIdChamado());
            
           ResultSet rs =  pst.executeQuery();
            
            while (rs.next()) {
                Tecnico tc = new  Tecnico ();
                tc.setNome(rs.getString("Nome"));
                               
                this.listaConsultaTec.add(tc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.listaConsultaTec;
       
    
     
   }
}
