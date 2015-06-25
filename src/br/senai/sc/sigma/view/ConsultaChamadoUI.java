package br.senai.sc.sigma.view;

import br.senai.sc.sigma.controller.AbrirChamadoController;
import br.senai.sc.sigma.controller.ConsultaController;
import br.senai.sc.sigma.controller.TecnicoController;
import br.senai.sc.sigma.model.Chamado;
import br.senai.sc.sigma.model.Intervencao;
import br.senai.sc.sigma.model.Tecnico;
import br.senai.sc.sigma.util.ChamadoTableModel;
import br.senai.sc.sigma.util.ConsultaChamadoTableModel;
import br.senai.sc.sigma.util.ConsultaTableModel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.MutableComboBoxModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ConsultaChamadoUI extends JInternalFrame {
	private JTable tableChamados;
	private JTextField jtfDescricao;
	private JTextField jtfSolucao;
	private Chamado chamado;
	private ArrayList<Tecnico> listaTecnico = new ConsultaController().listarTecnicoChamado();
	
	private JComboBox<Tecnico> jcbTecnico;
	
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaChamadoUI frame = new ConsultaChamadoUI(null);
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
	public ConsultaChamadoUI(final Chamado chamado) {
            this.chamado = chamado;
		setTitle("Consulta de Chamado");
		setFrameIcon(new ImageIcon(ConsultaChamadoUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 987, 569);
		
		JPanel panelChamado = new JPanel();
		panelChamado.setBorder(new TitledBorder(null, "Chamado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelDescricaoIntervencao = new JPanel();
		panelDescricaoIntervencao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Descri\u00E7\u00E3o da Interven\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            TelaPrincipalUI.obterInstancia().table.setModel(new ChamadoTableModel(new AbrirChamadoController().listarChamados()));
				dispose();
			}
		});
		
		JButton btnExcluirChamado = new JButton("Excluir Chamado");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelDescricaoIntervencao, GroupLayout.PREFERRED_SIZE, 855, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnExcluirChamado)
								.addGap(18)
								.addComponent(btnSair)
								.addGap(25))
							.addComponent(panelChamado, GroupLayout.PREFERRED_SIZE, 945, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(panelChamado, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(panelDescricaoIntervencao, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSair)
						.addComponent(btnExcluirChamado))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		
		JLabel lblTcnico = new JLabel("Tecnico :");
        //jcbTecnico = new JComboBox();
        jcbTecnico = new JComboBox<Tecnico>();

		DefaultComboBoxModel<Tecnico> modelCliente = new DefaultComboBoxModel<Tecnico>();
				for (Tecnico tecnico : listaTecnico) {
					
					((MutableComboBoxModel<Tecnico>) jcbTecnico).addElement(tecnico);
				}
		

		
		JLabel lblDescrio = new JLabel("Descricao:");
                jtfDescricao = new JTextField(this.chamado.getDescricao());
		jtfDescricao.setColumns(10);
                
                
		
		JLabel lblNewLabel = new JLabel("Solucao :");
		Intervencao interv = new Intervencao();
		jtfSolucao = new JTextField(interv.getSolucao());
		jtfSolucao.setColumns(10);
		GroupLayout gl_panelDescricaoIntervencao = new GroupLayout(panelDescricaoIntervencao);
		gl_panelDescricaoIntervencao.setHorizontalGroup(
			gl_panelDescricaoIntervencao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDescricaoIntervencao.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panelDescricaoIntervencao.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDescricaoIntervencao.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDescrio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addGap(80)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtfSolucao, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelDescricaoIntervencao.createSequentialGroup()
							.addComponent(lblTcnico)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jcbTecnico, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(114, Short.MAX_VALUE))
		);
		gl_panelDescricaoIntervencao.setVerticalGroup(
			gl_panelDescricaoIntervencao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDescricaoIntervencao.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panelDescricaoIntervencao.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTcnico)
						.addComponent(jcbTecnico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_panelDescricaoIntervencao.createParallelGroup(Alignment.LEADING)
						.addComponent(jtfSolucao, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescrio))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		panelDescricaoIntervencao.setLayout(gl_panelDescricaoIntervencao);
		
		JScrollPane scrollPaneChamados = new JScrollPane();
		GroupLayout gl_panelChamado = new GroupLayout(panelChamado);
		gl_panelChamado.setHorizontalGroup(
			gl_panelChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelChamado.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneChamados, GroupLayout.PREFERRED_SIZE, 910, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panelChamado.setVerticalGroup(
			gl_panelChamado.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelChamado.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addComponent(scrollPaneChamados, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
		);
		
                
                ///////////////////
                tableChamados = new JTable();
		tableChamados.setModel(new ConsultaChamadoTableModel(new ConsultaController().listarChamadosPorChamado(chamado)));
		tableChamados.getColumnModel().getColumn(0).setResizable(false);
		tableChamados.getColumnModel().getColumn(1).setResizable(false);
		tableChamados.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableChamados.getColumnModel().getColumn(3).setResizable(false);
		tableChamados.getColumnModel().getColumn(4).setResizable(false);
		scrollPaneChamados.setViewportView(tableChamados);
		panelChamado.setLayout(gl_panelChamado);
                
		getContentPane().setLayout(groupLayout);

	}
}
