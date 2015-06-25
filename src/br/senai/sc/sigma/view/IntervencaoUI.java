package br.senai.sc.sigma.view;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import br.senai.sc.sigma.controller.AbrirChamadoController;
import br.senai.sc.sigma.controller.ClienteController;
import br.senai.sc.sigma.controller.IntervencaoController;
import br.senai.sc.sigma.controller.TecnicoController;
import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.model.Intervencao;
import br.senai.sc.sigma.model.Tecnico;
import br.senai.sc.sigma.util.ChamadoTableModel;
import br.senai.sc.sigma.util.IntervencaoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IntervencaoUI extends JInternalFrame {

	private JTextField textFieldCliente;
	private JTextField textFieldDescricao;
	private JTextField textField_Solucao;
	private JFormattedTextField textField_DataInicio;
	private JFormattedTextField textField_DataFim;
	private Tecnico tecnico;

	private JComboBox comboBox_TipoAtendimento, comboBox_tecnico,
			comboBox_Status;

	public static JLabel lbldChamado, lblFim, lblInicio;

	private ArrayList<Tecnico> listaTecnicos = new TecnicoController()
			.listaTecnicos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntervencaoUI frame = new IntervencaoUI(null);
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
	public IntervencaoUI(final Chamado cham) {

		setTitle("Interven\u00E7\u00F5es");
		setFrameIcon(new ImageIcon(
				IntervencaoUI.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 716, 398);

		JLabel lblCodigo = new JLabel("Codigo Chamado :");

		lbldChamado = new JLabel("XXXXXX");

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (cham != null) {

					Intervencao interv = new Intervencao();

					SimpleDateFormat sdf = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm"); // view
					Date dataJavaUtil = null;

					try {
						interv.setDataFinalIntervencao(sdf
								.parse(textField_DataFim.getText()));
						interv.setDataInicialIntervencao(sdf
								.parse(textField_DataInicio.getText()));
						interv.setChamado(cham);

						cham.getIdChamado();

						interv.setSolucao(textField_Solucao.getText());
						interv.setStatus((String) comboBox_Status
								.getSelectedItem());
						interv.setTipoAtendimento((String) comboBox_TipoAtendimento
								.getSelectedItem());
						interv.setTecnico((Tecnico) comboBox_tecnico
								.getSelectedItem());

						IntervencaoController ic = new IntervencaoController();
						ic.inserir(interv);

						JOptionPane.showMessageDialog(null,
								"Cadastrado com sucesso.");
						
						TelaPrincipalUI.obterInstancia().table.setModel(new ChamadoTableModel(new AbrirChamadoController().listarChamados()));
						
						dispose();

					} catch (Exception e) {

						e.printStackTrace();
					}

				}
			}
		});

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		DefaultComboBoxModel<Tecnico> modelTecnico = new DefaultComboBoxModel<Tecnico>();
		for (Tecnico tec : listaTecnicos

		) {
			modelTecnico.addElement(tec);
		}

		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(477)
																										.addComponent(
																												btnFechar)
																										.addGap(30)
																										.addComponent(
																												btnNewButton))
																						.addComponent(
																								panel_2,
																								GroupLayout.PREFERRED_SIZE,
																								686,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(22)
																		.addComponent(
																				lblCodigo)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lbldChamado)))
										.addContainerGap(4, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCodigo,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lbldChamado))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(panel_2,
												GroupLayout.PREFERRED_SIZE,
												287, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(btnFechar)
														.addComponent(
																btnNewButton))
										.addGap(235)));

		JLabel lblTecnico = new JLabel("Técnico :");

		comboBox_tecnico = new JComboBox();

		comboBox_tecnico.setModel(modelTecnico);

		JLabel lblSoluo = new JLabel("Soluções :");

		textField_Solucao = new JTextField();

		textField_Solucao.setColumns(10);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setEnabled(false);
		textFieldDescricao.setEditable(false);

		textFieldDescricao.setColumns(10);

		textFieldCliente = new JTextField();
		textFieldCliente.setEnabled(false);
		textFieldCliente.setEditable(false);
		textFieldCliente.setColumns(10);

		JLabel lblCliente = new JLabel("Cliente :");

		JLabel lblDescrio = new JLabel("Descrições :");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Periodo", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JLabel lblInicio = new JLabel("Inicio :");

		JLabel lblFim = new JLabel("Fim :");

		textField_DataInicio = new JFormattedTextField();
		textField_DataInicio.setColumns(10);

		DefaultFormatterFactory format;
		try {
			format = new DefaultFormatterFactory(new MaskFormatter(
					"##/##/#### ##:##"));
			textField_DataInicio.setFormatterFactory(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		textField_DataFim = new JFormattedTextField();
		textField_DataFim.setColumns(10);

		DefaultFormatterFactory format1;
		try {
			format1 = new DefaultFormatterFactory(new MaskFormatter(
					"##/##/#### ##:##"));
			textField_DataFim.setFormatterFactory(format1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblInicio)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		textField_DataInicio,
																		GroupLayout.PREFERRED_SIZE,
																		142,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblFim)
																.addGap(18)
																.addComponent(
																		textField_DataFim,
																		GroupLayout.PREFERRED_SIZE,
																		142,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(40, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														textField_DataInicio,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblInicio))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblFim)
												.addComponent(
														textField_DataFim,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JLabel lblStatus = new JLabel("Status :");

		comboBox_TipoAtendimento = new JComboBox();
		comboBox_TipoAtendimento.setModel(new DefaultComboBoxModel(
				new String[] { "Atendimento Remoto", "Atendimento Externo",
						"Administrativo", "Garantia" }));

		JLabel lblTipoAtendimento = new JLabel("Tipo Atendimento:");

		comboBox_Status = new JComboBox();
		comboBox_Status.setModel(new DefaultComboBoxModel(new String[] {
				"Pendente", "Executando", "Aguardando Peça", "Concluido",
				"Aguardando Cliente" }));

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_panel_2
																										.createSequentialGroup()
																										.addGap(13)
																										.addComponent(
																												lblCliente)
																										.addGap(10)
																										.addComponent(
																												textFieldCliente,
																												GroupLayout.PREFERRED_SIZE,
																												310,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_panel_2
																										.createSequentialGroup()
																										.addGap(6)
																										.addGroup(
																												gl_panel_2
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addGroup(
																																gl_panel_2
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblTecnico)
																																		.addGap(18)
																																		.addComponent(
																																				comboBox_tecnico,
																																				GroupLayout.PREFERRED_SIZE,
																																				261,
																																				GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																gl_panel_2
																																		.createSequentialGroup()
																																		.addGap(397)
																																		.addComponent(
																																				lblStatus)
																																		.addGap(18)
																																		.addComponent(
																																				comboBox_Status,
																																				0,
																																				194,
																																				Short.MAX_VALUE)))))
																		.addGap(23))
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								Alignment.TRAILING,
																								gl_panel_2
																										.createSequentialGroup()
																										.addComponent(
																												lblSoluo,
																												GroupLayout.PREFERRED_SIZE,
																												53,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(4)
																										.addComponent(
																												textField_Solucao,
																												GroupLayout.PREFERRED_SIZE,
																												310,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								Alignment.TRAILING,
																								gl_panel_2
																										.createSequentialGroup()
																										.addComponent(
																												lblDescrio)
																										.addGap(10)
																										.addComponent(
																												textFieldDescricao,
																												GroupLayout.PREFERRED_SIZE,
																												310,
																												GroupLayout.PREFERRED_SIZE)))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								gl_panel_2
																										.createSequentialGroup()
																										.addComponent(
																												lblTipoAtendimento)
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												comboBox_TipoAtendimento,
																												GroupLayout.PREFERRED_SIZE,
																												172,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								panel,
																								GroupLayout.PREFERRED_SIZE,
																								246,
																								GroupLayout.PREFERRED_SIZE))
																		.addContainerGap(
																				23,
																				Short.MAX_VALUE)))));
		gl_panel_2
				.setVerticalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_panel_2
																										.createSequentialGroup()
																										.addContainerGap()
																										.addGroup(
																												gl_panel_2
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addGroup(
																																gl_panel_2
																																		.createSequentialGroup()
																																		.addGap(3)
																																		.addComponent(
																																				lblCliente))
																														.addComponent(
																																textFieldCliente,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)))
																						.addGroup(
																								gl_panel_2
																										.createSequentialGroup()
																										.addGap(28)
																										.addGroup(
																												gl_panel_2
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																lblStatus)
																														.addComponent(
																																comboBox_Status,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE))))
																		.addGap(42)
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblTipoAtendimento)
																						.addComponent(
																								comboBox_TipoAtendimento,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGap(49)
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblDescrio)
																						.addComponent(
																								textFieldDescricao,
																								GroupLayout.PREFERRED_SIZE,
																								76,
																								GroupLayout.PREFERRED_SIZE))))
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGap(18)
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblSoluo)
																						.addComponent(
																								textField_Solucao,
																								GroupLayout.PREFERRED_SIZE,
																								76,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblTecnico)
																						.addComponent(
																								comboBox_tecnico,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGap(36)
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				91,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(35, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		getContentPane().setLayout(groupLayout);

		if (cham != null) {
			textFieldCliente.setText(cham.getCliente().getNome());
			textFieldDescricao.setText(cham.getDescricao());
			lbldChamado.setText(cham.getIdChamado().toString());

		}
	}
}
