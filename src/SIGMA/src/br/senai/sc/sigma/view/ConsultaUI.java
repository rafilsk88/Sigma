package br.senai.sc.sigma.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.senai.sc.sigma.controller.ClienteController;
import br.senai.sc.sigma.controller.TecnicoController;
import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.model.Tecnico;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

public class ConsultaUI extends JInternalFrame {
	private JTextField jtfNumeroChamado;
	private JComboBox<Cliente> jcbCliente;
	private JComboBox<Tecnico>  jcbTecnico;
	

	private ArrayList<Tecnico> listaTecnicos = new TecnicoController()
			.listaTecnicos();

	private ArrayList<Cliente> listaClientes = new ClienteController()
			.listaClientes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaUI frame = new ConsultaUI();
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
	public ConsultaUI() {
		setTitle("Consulta");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setFrameIcon(new ImageIcon(
				ConsultaUI.class
						.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		setBounds(100, 100, 628, 306);

		JPanel panelCliente = new JPanel();
		panelCliente.setBorder(new TitledBorder(null, "Por Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelTecnico = new JPanel();
		panelTecnico.setBorder(new TitledBorder(null, "Por Tecnico",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelChamado = new JPanel();
		panelChamado.setBorder(new TitledBorder(null, "Por Chamado",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		 //		?????????????       VERIFICAR		?????????????      //
		 ////////////////////   BOT�O CONSULTAR      ////////////////////
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				if ( jcbCliente.getSelectedIndex() > -1 ) {
					Cliente c = (Cliente) jcbCliente.getSelectedItem();
					ConsultaClienteUI conClientUI = new ConsultaClienteUI(c);
					conClientUI.setFocusable(true);
					conClientUI.moveToFront();
					conClientUI.requestFocus();
					TelaPrincipalUI.obterInstancia().getContentPane().add(conClientUI, 0);
					try {
						conClientUI.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					conClientUI.setVisible(true);
					
								
				} else if ( !jtfNumeroChamado.getText().equals("")) {
					Chamado chamado = new Chamado();
					chamado.setIdChamado(Integer.parseInt(jtfNumeroChamado.getText()) );
					ConsultaChamadoUI conChamadotUI = new ConsultaChamadoUI(chamado);
					conChamadotUI.setFocusable(true);
					conChamadotUI.moveToFront();
					conChamadotUI.requestFocus();
					TelaPrincipalUI.obterInstancia().getContentPane().add(conChamadotUI, 0);
					try {
						conChamadotUI.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					conChamadotUI.setVisible(true);
										
				} else if ( jcbTecnico.getSelectedIndex() > -1 ) {
					Tecnico tecnico = (Tecnico) jcbTecnico.getSelectedItem();
					ConsultaTecnicoUI conTecnicoUI = new ConsultaTecnicoUI(tecnico);
					conTecnicoUI.setFocusable(true);
					conTecnicoUI.moveToFront();
					conTecnicoUI.requestFocus();
					TelaPrincipalUI.obterInstancia().getContentPane().add(conTecnicoUI, 0);
					try {
						conTecnicoUI.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					conTecnicoUI.setVisible(true);					
								
				}
				dispose();
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		 ////////////////////   FIM DA CONSULTA      ////////////////////
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelCliente, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(panelChamado, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelTecnico, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnConsultar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(panelCliente, 0, 0, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(panelChamado, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(panelTecnico, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addGap(48))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnConsultar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(24))))
		);

		JLabel labelNumeroChamado = new JLabel("N\u00BA :");

		jtfNumeroChamado = new JTextField();
		jtfNumeroChamado.setColumns(10);
		GroupLayout gl_panelChamado = new GroupLayout(panelChamado);
		gl_panelChamado.setHorizontalGroup(
			gl_panelChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelChamado.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelNumeroChamado)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtfNumeroChamado, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_panelChamado.setVerticalGroup(
			gl_panelChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelChamado.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panelChamado.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelNumeroChamado)
						.addComponent(jtfNumeroChamado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		panelChamado.setLayout(gl_panelChamado);

		JLabel labelTecnico = new JLabel(" T\u00E9cnico :");

		// aDICIONAR OS TECNICOS NO COMBOBOX
		jcbTecnico = new JComboBox<Tecnico>();

		DefaultComboBoxModel<Tecnico> modelTecnico = new DefaultComboBoxModel<Tecnico>();
		for (Tecnico tec : listaTecnicos) {
			modelTecnico.addElement(tec);
		}
		jcbTecnico.setModel(modelTecnico);
		jcbTecnico.setSelectedIndex(-1);

		GroupLayout gl_panelTecnico = new GroupLayout(panelTecnico);
		gl_panelTecnico.setHorizontalGroup(gl_panelTecnico.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panelTecnico
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(labelTecnico)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jcbTecnico, GroupLayout.PREFERRED_SIZE,
								261, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));
		gl_panelTecnico
				.setVerticalGroup(gl_panelTecnico
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelTecnico
										.createSequentialGroup()
										.addGap(20)
										.addGroup(
												gl_panelTecnico
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																labelTecnico)
														.addComponent(
																jcbTecnico,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(95, Short.MAX_VALUE)));
		panelTecnico.setLayout(gl_panelTecnico);

		JLabel labelNomeCliente = new JLabel("Nome :");

		// JComboBox comboBox_1 = new JComboBox();

		jcbCliente = new JComboBox<Cliente>();

		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cli : listaClientes) {
			modelCliente.addElement(cli);
		}
		jcbCliente.setModel(modelCliente);
		jcbCliente.setSelectedIndex(-1);
		GroupLayout gl_panelCliente = new GroupLayout(panelCliente);
		gl_panelCliente.setHorizontalGroup(
			gl_panelCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCliente.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelNomeCliente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_panelCliente.setVerticalGroup(
			gl_panelCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCliente.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelNomeCliente)
						.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		panelCliente.setLayout(gl_panelCliente);
		getContentPane().setLayout(groupLayout);

	}
}
