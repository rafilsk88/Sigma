package br.senai.sc.sigma.view;

import java.awt.EventQueue;

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

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import br.senai.sc.sigma.controller.AbrirChamadoController;
import br.senai.sc.sigma.controller.ClienteController;
import br.senai.sc.sigma.controller.ConsultaController;
import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.util.ChamadoTableModel;
import br.senai.sc.sigma.util.ConsultaTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class ConsultaClienteUI extends JInternalFrame {
	private JTable tableChamados;
	private Cliente cliente;
	private JTextField jtfCnpj;

	private MaskFormatter mascaratelefone;
	private MaskFormatter mascaraCnpj;
	private MaskFormatter mascaraCpf;
	

	private static JTextField tf_Codigo, tf_NomeCliente;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClienteUI frame = new ConsultaClienteUI(null);
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
	public ConsultaClienteUI(final Cliente cli) {

		cliente = cli;
		setTitle("Consulta de Cliente");
		setFrameIcon(new ImageIcon(
				ConsultaClienteUI.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 806, 405);

		JPanel panelChamadosCliente = new JPanel();
		panelChamadosCliente.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Chamados do Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnExcluirCliente = new JButton("Excluir Cliente");
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int opcao = JOptionPane.showConfirmDialog(null,
						"Deseja realmente excluir este cliente ?",
						"Excluir Cliente", JOptionPane.YES_NO_OPTION);

				if (opcao == 0) {

					ClienteController cc = new ClienteController();
					try {
						cc.excluir(cliente);
						JOptionPane.showMessageDialog(null,
								"Cliente excluido com sucesso !");
                                                
                                         TelaPrincipalUI.obterInstancia().table.setModel(new ChamadoTableModel(new AbrirChamadoController().listarChamados()));
						dispose();

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

			}
		});

		JButton btnEditarCliente = new JButton("Editar Cliente"); 

		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				CadastroClienteUI cacli = new CadastroClienteUI( cli );
				cacli.setFocusable(true);
				cacli.moveToFront();
				cacli.requestFocus();
				TelaPrincipalUI.obterInstancia().getContentPane().add( cacli, 0 );
				try {
					cacli.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
                                TelaPrincipalUI.obterInstancia().table.setModel(new ChamadoTableModel(new AbrirChamadoController().listarChamados()));
				cacli.setVisible(true);
			}
		});
		
		JLabel lblCodigo = new JLabel("Codigo :");
		
		tf_Codigo = new JTextField();
		tf_Codigo.setEnabled(false);
		tf_Codigo.setEditable(false);
		tf_Codigo.setColumns(10);

		JLabel lblCliente = new JLabel("Cliente : ");
		
		tf_NomeCliente = new JTextField();
		tf_NomeCliente.setEnabled(false);
		tf_NomeCliente.setEditable(false);
		tf_NomeCliente.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelChamadosCliente, GroupLayout.PREFERRED_SIZE, 775, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 497, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnEditarCliente)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluirCliente)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSair))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tf_Codigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(111)
							.addComponent(lblCliente)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tf_NomeCliente, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(lblCliente)
						.addComponent(tf_Codigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_NomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(panelChamadosCliente, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSair)
						.addComponent(btnExcluirCliente)
						.addComponent(btnEditarCliente))
					.addContainerGap())
		);

		JScrollPane scrollPaneChamados = new JScrollPane();
		GroupLayout gl_panelChamadosCliente = new GroupLayout(
				panelChamadosCliente);
		gl_panelChamadosCliente.setHorizontalGroup(
			gl_panelChamadosCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelChamadosCliente.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneChamados, GroupLayout.PREFERRED_SIZE, 743, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelChamadosCliente.setVerticalGroup(
			gl_panelChamadosCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelChamadosCliente.createSequentialGroup()
					.addComponent(scrollPaneChamados, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(159, Short.MAX_VALUE))
		);

		tableChamados = new JTable();
		tableChamados.setModel(new ConsultaTableModel(new ConsultaController()
				.listarChamadosPorCliente(cli)));
		tableChamados.getColumnModel().getColumn(0).setResizable(false);
		tableChamados.getColumnModel().getColumn(1).setResizable(false);
		tableChamados.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableChamados.getColumnModel().getColumn(3).setResizable(false);
		tableChamados.getColumnModel().getColumn(4).setResizable(false);
		scrollPaneChamados.setViewportView(tableChamados);
		panelChamadosCliente.setLayout(gl_panelChamadosCliente);
		getContentPane().setLayout(groupLayout);
		
		if(cli != null)
		{
			tf_Codigo.setText(Integer.toString(cli.getIdCliente()));
			tf_NomeCliente.setText(cli.getNome());
		}
	}
}
