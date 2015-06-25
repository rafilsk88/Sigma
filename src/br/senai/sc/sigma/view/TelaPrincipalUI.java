package br.senai.sc.sigma.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.AbstractAction;


import br.senai.sc.sigma.controller.AbrirChamadoController;
import br.senai.sc.sigma.controller.IntervencaoController;
import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Intervencao;
import br.senai.sc.sigma.util.ChamadoTableModel;



import br.senai.sc.sigma.util.IntervencaoTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TelaPrincipalUI extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private static TelaPrincipalUI instancia;

	// SINGLETON
	public static TelaPrincipalUI obterInstancia() {
		if (instancia == null) {
			instancia = new TelaPrincipalUI();
		}
		return instancia;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
					TelaPrincipalUI frame = TelaPrincipalUI.obterInstancia();
					frame.setVisible(true);
					frame.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipalUI() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						TelaPrincipalUI.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		setTitle("Sigma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 538);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroClienteUI cadCli = new CadastroClienteUI(null);
				cadCli.setFocusable(true);
				cadCli.moveToFront();
				cadCli.requestFocus();
				getContentPane().add(cadCli, 0);
				try {
					cadCli.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cadCli.setVisible(true);

			}
		});
		mnCadastro.add(mntmCliente);

		JMenuItem mntmTcnico = new JMenuItem("Tecnico");
		mntmTcnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroTecnicoUI cadTec = new CadastroTecnicoUI(null);
				cadTec.setFocusable(true);
				cadTec.moveToFront();
				cadTec.requestFocus();
				getContentPane().add(cadTec, 0);
				try {
					cadTec.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cadTec.setVisible(true);

			}
		});
		mnCadastro.add(mntmTcnico);

		JMenu mnChamado = new JMenu("Chamado");
		menuBar.add(mnChamado);

		JMenuItem mntmAbrirChamado = new JMenuItem("Abrir Chamado");
		mntmAbrirChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbrirChamadoUI abr = new AbrirChamadoUI();
				abr.setFocusable(true);
				abr.moveToFront();
				abr.requestFocus();
				getContentPane().add(abr, 0);
				try {
					abr.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				abr.setVisible(true);

			}
		});
		mnChamado.add(mntmAbrirChamado);

		JMenu mnConsulta = new JMenu("Consulta");
		mnConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				ConsultaUI con = new ConsultaUI();
				con.setFocusable(true);
				con.moveToFront();
				con.requestFocus();
				getContentPane().add(con, 0);
				try {
					con.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				con.setVisible(true);
			}
		});

		menuBar.add(mnConsulta);

		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});

		menuBar.add(mnSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaPrincipalUI.class
				.getResource("/logo- sigma.fw.png")));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
							.addGap(13))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
					.addContainerGap())
		);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IntervencaoUI inter = new IntervencaoUI(new AbrirChamadoController().listarChamados().get(table.getSelectedRow()));
                              
                                
				inter.setFocusable(true);
				inter.moveToFront();
				inter.requestFocus();
				
				getContentPane().add(inter, 0);
				inter.setVisible(true);
			}
		});
		
		table.setModel(new ChamadoTableModel(new AbrirChamadoController().listarChamados()));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		table.repaint();
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
