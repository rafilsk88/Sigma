package br.senai.sc.sigma.view;


import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;

import br.senai.sc.sigma.controller.AbrirChamadoController;
import br.senai.sc.sigma.controller.ClienteController;
import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.util.ChamadoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;

public class AbrirChamadoUI extends JInternalFrame {
	private JTextField textFieldTituloDoChamado;
	private JTextField textFieldDescricaoDoProblema;
	private JFormattedTextField textFieldDataAgendamento;
	private JComboBox<Cliente> comboBoxCliente;
	private JComboBox<Chamado> cbPrioridade;
	private Chamado ch;

	private ArrayList<Cliente> listaClientes = new ClienteController()
			.listaClientes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AbrirChamadoUI frame = new AbrirChamadoUI();
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
	public AbrirChamadoUI() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Chamados ");
		setFrameIcon(new ImageIcon(
				AbrirChamadoUI.class
						.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		setBounds(100, 100, 697, 517);

		/* SALVANDO O CHAMADO */

		JButton btnSalvar = new JButton("Salvar");

		btnSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ch = new Chamado();

				/* TRANSFORMAR A DATA */

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // view
				Date dataJavaUtil = null;

				try {
					dataJavaUtil = sdf.parse(textFieldDataAgendamento.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * PEGA OS VALORES DA TELA PRINCIPAL ENVIA PARA CLASS CHAMADO
				 */

				ch.setTituloChamado(textFieldTituloDoChamado.getText());
				ch.setDescricao(textFieldDescricaoDoProblema.getText());
				ch.setCliente((Cliente) comboBoxCliente.getSelectedItem());
				ch.setPrioridade((String) cbPrioridade.getSelectedItem());
				ch.setDataAgendamento(dataJavaUtil);

				/* CHAMA A CLASS CONTROLLER */

				AbrirChamadoController ac = new AbrirChamadoController();
				
				try {
					ac.inserir(ch);
					
													
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                             JOptionPane.showMessageDialog(null,
							"Cadastrado com sucesso.");
                   
					TelaPrincipalUI.obterInstancia().table.setModel(new ChamadoTableModel(new AbrirChamadoController().listarChamados()));
                                    
					dispose();
			}
		});
                

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Abrir Chamado",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 661, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null,
				"Descri\u00E7\u00E3o do Chamado", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JButton btnCnacelar = new JButton("Cancelar");
		btnCnacelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JButton btnLimpar = new JButton("Limpar");

		/* LIMPA OS CAMPOS DA TELA PRINCIPAL */

		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldTituloDoChamado.setText("");
				textFieldDescricaoDoProblema.setText("");
				textFieldDataAgendamento.setText("");
				textFieldTituloDoChamado.requestFocus();
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(26)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														panel_1,
														GroupLayout.PREFERRED_SIZE,
														541,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.TRAILING)
																.addGroup(
																		gl_panel.createSequentialGroup()
																				.addComponent(
																						btnLimpar)
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addComponent(
																						btnCnacelar)
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addComponent(
																						btnSalvar))
																.addComponent(
																		panel_2,
																		GroupLayout.PREFERRED_SIZE,
																		608,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(89, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(24)
								.addComponent(panel_1,
										GroupLayout.PREFERRED_SIZE, 69,
										GroupLayout.PREFERRED_SIZE)
								.addGap(26)
								.addComponent(panel_2,
										GroupLayout.PREFERRED_SIZE, 296,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 9,
										Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnSalvar)
												.addComponent(btnCnacelar)
												.addComponent(btnLimpar))));

		JLabel lblTituloDoChamado = new JLabel("Titulo do Chamado :");

		textFieldTituloDoChamado = new JTextField();
		textFieldTituloDoChamado.setColumns(10);

		JLabel lblDescrioDoProblema = new JLabel(
				"Descri\u00E7\u00E3o do problema :");

		textFieldDescricaoDoProblema = new JTextField();
		textFieldDescricaoDoProblema.setColumns(10);

		JLabel lblPrioridade = new JLabel("Prioridade :");

		JLabel lblDataDeAgendamento = new JLabel("Data de agendamento :");

		/* FORMATO DA DATA DE AGENDAMENTO */

		textFieldDataAgendamento = new JFormattedTextField();
		textFieldDataAgendamento.setColumns(10);
		DefaultFormatterFactory format;
		try {
			format = new DefaultFormatterFactory(new MaskFormatter(
					"##/##/#### ##:##"));
			textFieldDataAgendamento.setFormatterFactory(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		/* INSERE OS DADOS NO COMBOBOX */

		cbPrioridade = new JComboBox<Chamado>();

		cbPrioridade.setModel(new DefaultComboBoxModel(new String[] {"Alta", "Media", "Baixa" }));

		cbPrioridade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblDataDeAgendamento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldDataAgendamento, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTituloDoChamado)
								.addComponent(lblDescrioDoProblema))
							.addGap(10)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldDescricaoDoProblema, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldTituloDoChamado, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblPrioridade)
							.addGap(18)
							.addComponent(cbPrioridade, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTituloDoChamado)
						.addComponent(textFieldTituloDoChamado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescrioDoProblema)
						.addComponent(textFieldDescricaoDoProblema, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPrioridade)
						.addComponent(cbPrioridade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeAgendamento)
						.addComponent(textFieldDataAgendamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		panel_2.setLayout(gl_panel_2);

		JLabel lblBuscar = new JLabel("Buscar :");

		/*	RECEBE OS CLIENTE CADASTRADOS*/
		
		comboBoxCliente = new JComboBox<Cliente>();

		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cli : listaClientes) {
			modelCliente.addElement(cli);
		}
		comboBoxCliente.setModel(modelCliente);

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroClienteUI cadCli = new CadastroClienteUI(null);
				cadCli.setFocusable(true);
				cadCli.moveToFront();
				cadCli.requestFocus();
				TelaPrincipalUI.obterInstancia().getContentPane()
						.add(cadCli, 0);
				cadCli.setVisible(true);
			}
		});

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblBuscar)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(comboBoxCliente,
								GroupLayout.PREFERRED_SIZE, 345,
								GroupLayout.PREFERRED_SIZE).addGap(34)
						.addComponent(btnIncluir)
						.addContainerGap(127, Short.MAX_VALUE)));
		gl_panel_1
				.setVerticalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblBuscar)
														.addComponent(
																comboBoxCliente,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnIncluir))
										.addContainerGap(21, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
