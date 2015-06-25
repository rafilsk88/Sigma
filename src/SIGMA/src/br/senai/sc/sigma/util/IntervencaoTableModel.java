package br.senai.sc.sigma.util;

import br.senai.sc.sigma.model.Intervencao;
import br.senai.sc.sigma.view.IntervencaoUI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class IntervencaoTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int  chamado_idchamado = 0;
	private static final int  tecnico_matricula = 1;
	private static final int datahoraInicialIntervencao = 2;
	private static final int datahoraFinalIntervencao = 3;
	private static final int istatu = 4;

	private List<Intervencao> valores;

	// Esse e um construtor, que recebe a nossa lista de clientes
	public IntervencaoTableModel(List<Intervencao> valores) {
		this.valores = new ArrayList<Intervencao>(valores);
	}

   

	public int getRowCount() {
		// Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		// Quantas colunas tem a tabela? Nesse exemplo, sï¿½ 2.
		return 5;
	}

	public String getColumnName(int column) {
		// Qual ï¿½ o nome das nossas colunas?
		if (column == chamado_idchamado)
			return "Numero do Chamado";
		if (column == tecnico_matricula)
			return "Nome Técnico";
		if (column == datahoraInicialIntervencao)
			return "Data Inicial";
		if (column == datahoraFinalIntervencao)
			return "Data Final";
		if (column == istatu)
			return "Status";
		
		
		return null;
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		Intervencao intervencao = valores.get(row);
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // view
		if (column == chamado_idchamado)
			return intervencao.getChamado();
		if (column == tecnico_matricula)
			return intervencao.getTecnico();
		if (column == datahoraInicialIntervencao)
			return intervencao.getDataInicialIntervencao();//sdf.format
		if (column == datahoraFinalIntervencao)
			return intervencao.getDataFinalIntervencao();//sdf.format
		if (column == istatu)
			return intervencao.getStatus();
		return null;

	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Intervencao intervencao = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no parï¿½metro.
		// Note que vc poderia alterar 2 campos ao invï¿½s de um sï¿½.
		if (columnIndex == chamado_idchamado)
			intervencao.getChamado().setIdChamado(null);
		if (columnIndex == tecnico_matricula)
			intervencao.getTecnico().setNome(null); 
		if (columnIndex == datahoraInicialIntervencao)
			intervencao.setDataInicialIntervencao(null);
		if (columnIndex == datahoraFinalIntervencao)
			intervencao.setDataFinalIntervencao(null); 
		if (columnIndex == istatu)
			intervencao.setStatus(null);
		
	}

	public Class<?> getColumnClass(int columnIndex) {
		// Qual a classe das nossas colunas? Como estamos exibindo texto, ï¿½
		// string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Indicamos se a cï¿½lula da rowIndex e da columnIndex ï¿½ editï¿½vel.
		// Nossa tabela toda ï¿½.
		return true;
	}

	// Jï¿½ que esse tableModel ï¿½ de clientes, vamos fazer um get que retorne
	// um objeto cliente inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public Intervencao get(int row) {
		return valores.get(row);
	}
}
