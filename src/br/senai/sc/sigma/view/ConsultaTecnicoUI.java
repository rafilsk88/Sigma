package br.senai.sc.sigma.view;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import br.senai.sc.sigma.controller.AbrirChamadoController;
import br.senai.sc.sigma.controller.ClienteController;
import br.senai.sc.sigma.controller.ConsultaController;
import br.senai.sc.sigma.controller.TecnicoController;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.model.Tecnico;
import br.senai.sc.sigma.util.ChamadoTableModel;
import br.senai.sc.sigma.util.ConsultaTableModel;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

public class ConsultaTecnicoUI extends JInternalFrame {
	private JTable tableChamadosTecnico;
	private Tecnico tecnico;
	private JTextField tf_CodigoTecnico;
	private JTextField textField;

	// private JTextField jtfFuncao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaTecnicoUI frame = new ConsultaTecnicoUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 	/**
	 * Create the frame.
	 */
	public ConsultaTecnicoUI(final Tecnico tec) {
		this.tecnico = tec;
		setTitle("Consulta do T�cnico");
		setFrameIcon(new ImageIcon(
				ConsultaTecnicoUI.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 869, 436);
		
		

		JPanel panel_Chamados_Tecnico = new JPanel();
		panel_Chamados_Tecnico.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Chamados do Tecnico",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		///////////	 BOTAO EXCLUIR 	/////////////////
		JButton btnExcluirTecnico = new JButton("Excluir T�cnico");
		btnExcluirTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcao = JOptionPane.showConfirmDialog(null,
						"Deseja realmente excluir este tecnico ?",
						"Excluir Tecnico", JOptionPane.YES_NO_OPTION);

				if (opcao == 0) {

					TecnicoController tc = new TecnicoController();
					try {
						tc.excluir(tecnico.getMatricula());
						JOptionPane.showMessageDialog(null,
								"Tecnico excluido com sucesso !");
                                                
                                                TelaPrincipalUI.obterInstancia().table.setModel(new ChamadoTableModel(new AbrirChamadoController().listarChamados()));
						dispose();

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});

		JButton btnEditarTecnico = new JButton("Editar T�cnico");
		btnEditarTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadastroTecnicoUI cadtec = new CadastroTecnicoUI( tec );
				cadtec.setFocusable(true);
				cadtec.moveToFront();
				cadtec.requestFocus();
				TelaPrincipalUI.obterInstancia().getContentPane().add( cadtec, 0 );
				try {
					cadtec.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
				cadtec.setVisible(true);
				
			}	
		});
		
		JLabel lblCodigo = new JLabel("Codigo :");
		
		tf_CodigoTecnico = new JTextField();
		tf_CodigoTecnico.setEnabled(false);
		tf_CodigoTecnico.setEditable(false);
		tf_CodigoTecnico.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome T\u00E9cnico :");
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_Chamados_Tecnico, GroupLayout.PREFERRED_SIZE, 841, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblCodigo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tf_CodigoTecnico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(123)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(556, Short.MAX_VALUE)
					.addComponent(btnEditarTecnico)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnExcluirTecnico)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair)
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(tf_CodigoTecnico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(panel_Chamados_Tecnico, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSair)
						.addComponent(btnExcluirTecnico)
						.addComponent(btnEditarTecnico))
					.addContainerGap())
		);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_Chamados_Tecnico = new GroupLayout(
				panel_Chamados_Tecnico);
		gl_panel_Chamados_Tecnico.setHorizontalGroup(
			gl_panel_Chamados_Tecnico.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Chamados_Tecnico.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 806, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panel_Chamados_Tecnico.setVerticalGroup(
			gl_panel_Chamados_Tecnico.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Chamados_Tecnico.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
					.addContainerGap())
		);

		tableChamadosTecnico = new JTable();

		tableChamadosTecnico.setModel(new ConsultaTableModel(
				new ConsultaController().listarChamadosPorTecnico(tec)));

		tableChamadosTecnico.getColumnModel().getColumn(0)
				.setPreferredWidth(46);
		tableChamadosTecnico.getColumnModel().getColumn(1)
				.setPreferredWidth(85);
		tableChamadosTecnico.getColumnModel().getColumn(2)
				.setPreferredWidth(150);
		tableChamadosTecnico.getColumnModel().getColumn(3)
				.setPreferredWidth(150);
		tableChamadosTecnico.getColumnModel().getColumn(4)
				.setPreferredWidth(85);
		tableChamadosTecnico.getColumnModel().getColumn(5)
				.setPreferredWidth(70);
		scrollPane.setViewportView(tableChamadosTecnico);
		panel_Chamados_Tecnico.setLayout(gl_panel_Chamados_Tecnico);
		getContentPane().setLayout(groupLayout);

		if(tec != null)
		{
			tf_CodigoTecnico.setText(Integer.toString(tec.getMatricula()));
			textField.setText(tec.getNome());
		}
		
	}
}
