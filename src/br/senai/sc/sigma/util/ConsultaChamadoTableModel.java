package br.senai.sc.sigma.util;

import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ConsultaChamadoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int CODIGO = 0;
        private static final int DATAABERTURA = 1;
        private static final int DATAAGENDAMENTO = 2;
	private static final int PRIORIDADE = 3;
        private static final int TITULO = 4;
	private static final int STATU = 5;//
       // private static final int CLINOME = 6;//
	

	private List<Chamado> valores;

	// Esse e um construtor, que recebe a nossa lista de clientes
	public ConsultaChamadoTableModel(List<Chamado> valores) {
		this.valores = new ArrayList<Chamado>(valores);
	}

	public int getRowCount() {
		// Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		// Quantas colunas tem a tabela? Nesse exemplo, s� 2.
		return 6;
	}

	public String getColumnName(int column) {
		// Qual � o nome das nossas colunas?
		if (column == CODIGO)
			return "Codigo";
		if (column == PRIORIDADE)
			return "Prioridade";
              
		if (column == DATAABERTURA)
			return "Data Abertura";
		if (column == TITULO)
			return "Titulo";
		if (column == DATAAGENDAMENTO)
			return "Data Agendamento";
                if (column == STATU)
			return "Status";
              //  if (column == CLINOME)
		//	return "Nome do Cliente";
		return null;
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		Chamado chamados = valores.get(row);
		
		if (column == CODIGO)
			return chamados.getIdChamado();
		if (column == PRIORIDADE)
			return chamados.getPrioridade();
               
		if (column == DATAABERTURA)
			return chamados.getDataAbertura();
		if (column == TITULO)
			return chamados.getTituloChamado();
		if (column == DATAAGENDAMENTO)
			return chamados.getDataAgendamento();
                if (column == STATU)
			return chamados.getStatu();
              //  if (column == CLINOME)
		//	return chamados.getCliente().getNome();
		
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
		if (columnIndex == TITULO)
			chamados.setTituloChamado(aValue.toString());; 
		if (columnIndex == DATAAGENDAMENTO)
			chamados.setDataAgendamento(null);
                if (columnIndex == STATU)
			chamados.setStatu(aValue.toString());
                // if (columnIndex == CLINOME)
			//chamados.setCliente(Cliente c.);
	}

	public Class<?> getColumnClass(int columnIndex) {
		// Qual a classe das nossas colunas? Como estamos exibindo texto, �
		// string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Indicamos se a c�lula da rowIndex e da columnIndex � edit�vel.
		// Nossa tabela toda �.
		return false;
	}

	// J� que esse tableModel � de clientes, vamos fazer um get que retorne
	// um objeto cliente inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public Chamado get(int row) {
		return valores.get(row);
	}
}
