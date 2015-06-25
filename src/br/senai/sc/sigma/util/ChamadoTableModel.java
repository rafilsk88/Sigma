package br.senai.sc.sigma.util;

import br.senai.sc.sigma.model.Chamado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ChamadoTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int CODIGO = 0;
	private static final int PRIORIDADE = 1;
	private static final int DATAABERTURA = 2;
	private static final int CLIENTE = 3;
	private static final int TITULO = 4;
	private static final int DATAAGENDAMENTO = 5;
	private static final int STATUS = 6;
	
	
	private List<Chamado> valores;
        private Chamado cha;

	// Esse e um construtor, que recebe a nossa lista de clientes
	public ChamadoTableModel(List<Chamado> valores) {
           
           
            	this.valores = new ArrayList<Chamado>(valores);
            
           
            
	}

	public int getRowCount() {
		// Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		// Quantas colunas tem a tabela? Nesse exemplo, s� 2.
		return 7;
	}

	public String getColumnName(int column) {
		// Qual � o nome das nossas colunas?
		if (column == CODIGO)
			return "Codigo";
		if (column == PRIORIDADE)
			return "Prioridade";
		if (column == DATAABERTURA)
			return "Data Abertura";
		if (column == CLIENTE)
			return "Cliente";
		if (column == TITULO)
			return "Titulo";
		if (column == DATAAGENDAMENTO)
			return "Data Agendamento";
		if (column == STATUS)
			return "Status";
		
		return null;
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		Chamado chamados = valores.get(row);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // view
		
		if (column == CODIGO)
			return chamados.getIdChamado();
		if (column == PRIORIDADE)
			return chamados.getPrioridade();
		if (column == DATAABERTURA)
			return sdf.format(chamados.getDataAbertura());
		if (column == CLIENTE)
			return chamados.getCliente().getNome();
		if (column == TITULO)
			return chamados.getTituloChamado();
		if (column == DATAAGENDAMENTO)
			return sdf.format(chamados.getDataAgendamento());
		if (column == STATUS)
			return chamados.getStatu();
		
		
		return ""; // Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Chamado chamados = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no par�metro.
		// Note que vc poderia alterar 2 campos ao inv�s de um s�.
		if (columnIndex == CODIGO)
			chamados.setIdChamado(null); // Verificar !!!
		if (columnIndex == PRIORIDADE)
			chamados.setPrioridade(aValue.toString());
		if (columnIndex == DATAABERTURA)
			chamados.setDataAbertura(null); 
		if (columnIndex == CLIENTE)
			chamados.getCliente().setNome(aValue.toString());
		if (columnIndex == TITULO)
			chamados.setTituloChamado(aValue.toString());; 
		if (columnIndex == DATAAGENDAMENTO)
			chamados.setDataAgendamento(null);
		if (columnIndex == STATUS)
			chamados.setStatu(null);
		
	}

	public Class<?> getColumnClass(int columnIndex) {
		// Qual a classe das nossas colunas? Como estamos exibindo texto, �
		// string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Indicamos se a c�lula da rowIndex e da columnIndex � edit�vel.
		// Nossa tabela toda �.
		return true;
	}

	// J� que esse tableModel � de clientes, vamos fazer um get que retorne
	// um objeto cliente inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public Chamado get(int row) {
		return valores.get(row);
	}
}
