package br.senai.sc.sigma.view;

import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import br.senai.sc.sigma.controller.AbrirChamadoController;
import br.senai.sc.sigma.controller.ClienteController;
import br.senai.sc.sigma.dao.ClienteDAO;
import br.senai.sc.sigma.model.Cliente;
import br.senai.sc.sigma.util.ChamadoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroClienteUI extends JInternalFrame {
	private JTextField tf_Codigo;
	private JTextField jtfNomeCliente;
	private JTextField jtfEndCliente;
	private JTextField jtfCidadeCliente;
	private JTextField jtfEstadoCliente;
	private JTextField jtfEmailCliente;
	private JFormattedTextField jtfCpfCliente;
	private JFormattedTextField jtfCnpjCliente;
	private JFormattedTextField jtfTelefoneCliente;
	
	private MaskFormatter mascaratelefone;
    private MaskFormatter mascaraCnpj;
    private MaskFormatter mascaraCpf;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClienteUI frame = new CadastroClienteUI(null);
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
	public CadastroClienteUI(final Cliente cli) {
		setClosable(true);
		setTitle("Cliente");
		setFrameIcon(new ImageIcon(
				CadastroClienteUI.class
						.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 526, 341);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastro de Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
					.addGap(76))
		);

			
			JLabel lblCodigo = new JLabel("Codigo :");
	
			tf_Codigo = new JTextField();
			tf_Codigo.setEnabled(false);
			tf_Codigo.setEditable(false);
			tf_Codigo.setColumns(10);
		
			JLabel lblNome = new JLabel("Nome :");

			jtfNomeCliente = new JTextField();
			jtfNomeCliente.setColumns(10);
	
			JLabel lblCpf = new JLabel("CPF :");
			// /// FORMATANDO A mascara do CNPJ \\\\\
				try {
                       mascaraCpf = new MaskFormatter("###.###.###-##");
                       jtfCpfCliente = new JFormattedTextField(mascaraCpf);

				} catch (ParseException e1) {
                       e1.printStackTrace();
				}
				jtfCpfCliente.setColumns(10);

				// /// FORMATANDO A mascara do CNPJ \\\\\
				JLabel lblCnpj = new JLabel("CNPJ :");
				try {
                       mascaraCnpj = new MaskFormatter("##.###.###/####-##");
                       jtfCnpjCliente = new JFormattedTextField(mascaraCnpj);

				} catch (ParseException e1) {
                       e1.printStackTrace();
				}
				jtfCnpjCliente.setColumns(10);

				JLabel lblEndereo = new JLabel("Endere\u00E7o :");

				jtfEndCliente = new JTextField();
				jtfEndCliente.setColumns(10);

				JLabel lblCidade = new JLabel("Cidade :");

				jtfCidadeCliente = new JTextField();
				jtfCidadeCliente.setColumns(10);

				JLabel lblEstado = new JLabel("Estado :");
				
				jtfEstadoCliente = new JTextField();
				jtfEstadoCliente.setColumns(10);

				///// FORMATANDO A TELEFONE  \\\\\
		
				JLabel lblTelefone = new JLabel("Telefone :");

				try {
					mascaratelefone = new MaskFormatter("(##) ####-####");
					jtfTelefoneCliente = new JFormattedTextField(mascaratelefone);
			
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				jtfTelefoneCliente.setColumns(10);

				JLabel lblEmail = new JLabel("Email :");

				jtfEmailCliente = new JTextField();
				jtfEmailCliente.setColumns(10);


				if(cli != null)
				{
					tf_Codigo.setText(Integer.toString(cli.getIdCliente()));
					tf_Codigo.setEnabled(false);
					tf_Codigo.setEditable(false);
					
					jtfNomeCliente.setText(cli.getNome());
					
					jtfCpfCliente.setText(cli.getCpf());
					jtfCpfCliente.setEnabled(false);
					jtfCpfCliente.setEditable(false);
					
					jtfCnpjCliente.setText(cli.getCnpj());
					jtfCnpjCliente.setEnabled(false);
					jtfCnpjCliente.setEditable(false);
					
					
					jtfCidadeCliente.setText(cli.getCidade());
					jtfEmailCliente.setText(cli.getEmail());
					jtfEndCliente.setText(cli.getEndereco());
					jtfEstadoCliente.setText(cli.getEstado());
					jtfTelefoneCliente.setText(cli.getTelefone());
				}
				
		
		

		
		///////////////////////////////////////////////////////////////////
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JButton jbSalvar = new JButton("Salvar");

		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				if (cli != null) {

					
					
					
					cli.setNome(jtfNomeCliente.getText());
					cli.setCpf(jtfCpfCliente.getText());
					cli.setCidade(jtfCidadeCliente.getText());
					cli.setEmail(jtfEmailCliente.getText());
					cli.setEndereco(jtfEndCliente.getText());
					cli.setEstado(jtfEstadoCliente.getText());
					cli.setCnpj(jtfCnpjCliente.getText());
					cli.setTelefone(jtfTelefoneCliente.getText());

					
					ClienteController cc = new ClienteController();
					try {
						cc.editar(cli);
						JOptionPane.showMessageDialog(null,
								"Cliente Editado com sucesso");
						
						TelaPrincipalUI.obterInstancia().table.setModel(new ChamadoTableModel(new AbrirChamadoController().listarChamados()));
						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						}
					

				} else {

					// Inserir
					Cliente cli = new Cliente();

					cli.setNome(jtfNomeCliente.getText());
					cli.setCpf(jtfCpfCliente.getText());
					cli.setCidade(jtfCidadeCliente.getText());
					cli.setEmail(jtfEmailCliente.getText());
					cli.setEndereco(jtfEndCliente.getText());
					cli.setEstado(jtfEstadoCliente.getText());
					cli.setCnpj(jtfCnpjCliente.getText());
					cli.setTelefone(jtfTelefoneCliente.getText());

					ClienteController con = new ClienteController();
					
					try {
						con.inserir(cli);;
						JOptionPane.showMessageDialog(null, "Cliente : " +cli.getNome()+ "\nCadastrado com sucesso");
						dispose();
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblEmail)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfEmailCliente, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTelefone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfTelefoneCliente)
									.addGap(221))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCidade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfCidadeCliente, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblEstado)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfEstadoCliente, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblEndereo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfEndCliente, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCpf)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfCpfCliente, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblCnpj)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfCnpjCliente, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 64, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfNomeCliente, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 18, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCodigo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tf_Codigo, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 320, GroupLayout.PREFERRED_SIZE)))
							.addGap(38))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(jbSalvar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jbCancelar)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(tf_Codigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(jtfNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(jtfCpfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCnpj)
						.addComponent(jtfCnpjCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereo)
						.addComponent(jtfEndCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(jtfCidadeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstado)
						.addComponent(jtfEstadoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(jtfTelefoneCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(jtfEmailCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancelar)
						.addComponent(jbSalvar))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
