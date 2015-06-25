package br.senai.sc.sigma.view;

import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import br.senai.sc.sigma.controller.TecnicoController;
import br.senai.sc.sigma.dao.TecnicoDAO;
import br.senai.sc.sigma.model.Tecnico;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;

public class CadastroTecnicoUI extends JInternalFrame {

    private JTextField jtfNomeTecnico;
    private JTextField jtfEndTecnico;
    private JFormattedTextField jtfTelefoneTecnico;
    private JTextField jtfEmailTecnico;
    private JFormattedTextField jtfCpfTecnico;
    private JFormattedTextField jtfRgTecnico;
    public JComboBox<String> cbFuncaoTecnico = new JComboBox<String>();
   

    private MaskFormatter mascatelefone;
    private MaskFormatter mascaraRg;
    private MaskFormatter mascaraCpf;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadastroTecnicoUI frame = new CadastroTecnicoUI(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void populaFuncao (){
    	
    	
    	DefaultComboBoxModel<String> sbm = new DefaultComboBoxModel<String>();
		
    	sbm.addElement("Analista de Redes nivel I");
    	sbm.addElement("Analista de Redes nivel II");
    	sbm.addElement("Auxiliar de Suporte nivel I");
    	sbm.addElement("Auxiliar de Suporte nivel II");
    	sbm.addElement("Auxiliar de Suporte nivel III");
    	
    	cbFuncaoTecnico.setModel(sbm);
    	
    }

   
    /**
     * Create the frame.
     *
     * @throws java.text.ParseException
     */
    public CadastroTecnicoUI(final Tecnico tec) {
        setClosable(true);
        setTitle("T\u00E9cnico");
        setBounds(100, 100, 530, 371);

        setFrameIcon(new ImageIcon(
                CadastroTecnicoUI.class
                .getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
        setIconifiable(true);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Cadastro de T\u00E9cnico",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
                Alignment.LEADING).addGroup(
                        groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 492,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
                Alignment.TRAILING).addGroup(
                        Alignment.LEADING,
                        groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 323,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(50, Short.MAX_VALUE)));

        JLabel lblNome = new JLabel("Nome :");

        jtfNomeTecnico = new JTextField();
        jtfNomeTecnico.setColumns(10);

        JLabel lblCpf = new JLabel("CPF :");
		// /// FORMATANDO A mascara do CNPJ \\\\\

        try {
            mascaraCpf = new MaskFormatter("###.###.###-##");
            jtfCpfTecnico = new JFormattedTextField(mascaraCpf);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        jtfCpfTecnico.setColumns(10);

        JLabel lblRg = new JLabel("RG :");
        // /// FORMATANDO A mascara do RG \\\\\
        try {
            mascaraRg = new MaskFormatter("##.###.###-#");
            jtfRgTecnico = new JFormattedTextField(mascaraRg);

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        jtfRgTecnico.setColumns(10);

        JLabel lblEndereo = new JLabel("Endere\u00E7o :");

        jtfEndTecnico = new JTextField();
        jtfEndTecnico.setColumns(10);

        JLabel lblTelefone = new JLabel("Telefone :");

        try {
            try {
                mascatelefone = new MaskFormatter("(##) ####-####");
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jtfTelefoneTecnico = new JFormattedTextField(mascatelefone);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        jtfTelefoneTecnico.setColumns(10);

        JLabel lblEmail = new JLabel("Email :");

        jtfEmailTecnico = new JTextField();
        jtfEmailTecnico.setColumns(10);

        //////////  Recebe as Strings do metodo populaFuncao \\\\\\\\\        
        cbFuncaoTecnico : populaFuncao();

        if (tec != null) 
        {
        	jtfNomeTecnico.setText(tec.getNome());
        	
        	jtfCpfTecnico.setText(tec.getCpf());
        	jtfCpfTecnico.setEnabled(false);
        	jtfCpfTecnico.setEditable(false);
        	
        	jtfEmailTecnico.setText(tec.getEmail());
        	jtfEndTecnico.setText(tec.getEndereco());
        	
        	jtfRgTecnico.setText(tec.getRg());
        	jtfRgTecnico.setEnabled(false);
        	jtfRgTecnico.setEditable(false);
        	
        	jtfTelefoneTecnico.setText(tec.getTelefone());
        	cbFuncaoTecnico.setSelectedItem(tec.getFuncao());
        	
        }

        JButton btnNewButton = new JButton("Cancelar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        
        JButton btnNewButton_1 = new JButton("Salvar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (tec != null) {

                    // Editar
                	
                    tec.setNome(jtfNomeTecnico.getText());
                    tec.setRg(jtfRgTecnico.getText());
                    tec.setEmail(jtfEmailTecnico.getText());
                    tec.setEndereco(jtfEndTecnico.getText());
                    tec.setTelefone(jtfTelefoneTecnico.getText());
                    tec.setCpf(jtfCpfTecnico.getText());
                    tec.setFuncao(cbFuncaoTecnico.getSelectedItem().toString());

                    TecnicoController tecon = new TecnicoController();

                    try {
                        tecon.editar(tec);
                        JOptionPane.showMessageDialog(null, " Editado com sucesso. ");
                        dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }

                } else {

                    // Inserir
                    Tecnico tec = new Tecnico();

                    tec.setNome(jtfNomeTecnico.getText());
                    tec.setCpf(jtfCpfTecnico.getText());
                    tec.setRg(jtfRgTecnico.getText());
                    tec.setEmail(jtfEmailTecnico.getText());
                    tec.setEndereco(jtfEndTecnico.getText());
                    tec.setTelefone(jtfTelefoneTecnico.getText());
                    tec.setFuncao(cbFuncaoTecnico.getSelectedItem().toString());
                    TecnicoController con = new TecnicoController();
                    try {
                        con.inserir(tec);
                        JOptionPane.showMessageDialog(null,
                                "Tecnico : " +tec.getNome()+ "\nCadastrado com sucesso.");
                        dispose();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }

                }

            }
        });

        JLabel lblFunao = new JLabel("Função :");

        JLabel lblCpf_1 = new JLabel("CPF :");

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(gl_panel
                .createParallelGroup(Alignment.TRAILING)
                .addGroup(
                        gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                gl_panel.createParallelGroup(
                                        Alignment.LEADING)
                                .addGroup(
                                        Alignment.TRAILING,
                                        gl_panel.createSequentialGroup()
                                        .addComponent(
                                                btnNewButton_1)
                                        .addPreferredGap(
                                                ComponentPlacement.UNRELATED)
                                        .addComponent(
                                                btnNewButton)
                                        .addGap(22))
                                .addGroup(
                                        gl_panel.createSequentialGroup()
                                        .addGroup(
                                                gl_panel.createParallelGroup(
                                                        Alignment.TRAILING,
                                                        false)
                                                .addGroup(
                                                        gl_panel.createSequentialGroup()
                                                        .addComponent(
                                                                lblCpf_1,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                28,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                ComponentPlacement.UNRELATED)
                                                        .addComponent(
                                                                jtfCpfTecnico,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                142,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                ComponentPlacement.RELATED,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(
                                                                lblRg)
                                                        .addGap(18)
                                                        .addComponent(
                                                                jtfRgTecnico,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                142,
                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGroup(
                                                        gl_panel.createSequentialGroup()
                                                        .addComponent(
                                                                lblNome)
                                                        .addPreferredGap(
                                                                ComponentPlacement.RELATED)
                                                        .addComponent(
                                                                jtfNomeTecnico,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                376,
                                                                GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(
                                                38,
                                                Short.MAX_VALUE))
                                .addGroup(
                                        gl_panel.createSequentialGroup()
                                        .addGroup(
                                                gl_panel.createParallelGroup(
                                                        Alignment.LEADING)
                                                .addGroup(
                                                        gl_panel.createSequentialGroup()
                                                        .addComponent(
                                                                lblFunao)
                                                        .addPreferredGap(
                                                                ComponentPlacement.RELATED)
                                                        .addComponent(
                                                                cbFuncaoTecnico,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                353,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                ComponentPlacement.RELATED,
                                                                33,
                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGroup(
                                                        gl_panel.createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addGroup(
                                                                Alignment.TRAILING,
                                                                gl_panel.createSequentialGroup()
                                                                .addComponent(
                                                                        lblEndereo)
                                                                .addPreferredGap(
                                                                        ComponentPlacement.RELATED)
                                                                .addComponent(
                                                                        jtfEndTecnico,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        376,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(
                                                                Alignment.TRAILING,
                                                                gl_panel.createSequentialGroup()
                                                                .addGroup(
                                                                        gl_panel.createParallelGroup(
                                                                                Alignment.LEADING)
                                                                        .addGroup(
                                                                                gl_panel.createSequentialGroup()
                                                                                .addComponent(
                                                                                        lblTelefone)
                                                                                .addPreferredGap(
                                                                                        ComponentPlacement.RELATED)
                                                                                .addComponent(
                                                                                        jtfTelefoneTecnico,
                                                                                        158,
                                                                                        158,
                                                                                        158))
                                                                        .addGroup(
                                                                                gl_panel.createSequentialGroup()
                                                                                .addComponent(
                                                                                        lblEmail)
                                                                                .addPreferredGap(
                                                                                        ComponentPlacement.RELATED)
                                                                                .addComponent(
                                                                                        jtfEmailTecnico,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        376,
                                                                                        GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(21))))
                                        .addContainerGap(
                                                38,
                                                Short.MAX_VALUE)))));
        gl_panel.setVerticalGroup(gl_panel
                .createParallelGroup(Alignment.LEADING)
                .addGroup(
                        gl_panel.createSequentialGroup()
                        .addGap(22)
                        .addGroup(
                                gl_panel.createParallelGroup(
                                        Alignment.BASELINE)
                                .addComponent(lblNome)
                                .addComponent(
                                        jtfNomeTecnico,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(26)
                        .addGroup(
                                gl_panel.createParallelGroup(
                                        Alignment.TRAILING)
                                .addGroup(
                                        gl_panel.createParallelGroup(
                                                Alignment.BASELINE)
                                        .addComponent(
                                                lblRg)
                                        .addComponent(
                                                lblCpf_1)
                                        .addComponent(
                                                jtfCpfTecnico,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addComponent(
                                        jtfRgTecnico,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(
                                gl_panel.createParallelGroup(
                                        Alignment.BASELINE)
                                .addComponent(lblEndereo)
                                .addComponent(
                                        jtfEndTecnico,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(
                                gl_panel.createParallelGroup(
                                        Alignment.BASELINE)
                                .addComponent(lblTelefone)
                                .addComponent(
                                        jtfTelefoneTecnico,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(
                                gl_panel.createParallelGroup(
                                        Alignment.BASELINE)
                                .addComponent(lblEmail)
                                .addComponent(
                                        jtfEmailTecnico,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(
                                gl_panel.createParallelGroup(
                                        Alignment.BASELINE)
                                .addComponent(lblFunao)
                                .addComponent(
                                        cbFuncaoTecnico,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED,
                                65, Short.MAX_VALUE)
                        .addGroup(
                                gl_panel.createParallelGroup(
                                        Alignment.BASELINE)
                                .addComponent(btnNewButton)
                                .addComponent(btnNewButton_1))
                        .addContainerGap()));
        panel.setLayout(gl_panel);
        getContentPane().setLayout(groupLayout);

    }
}
