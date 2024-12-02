import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class TelaProcurar extends JPanel {
	private JButton btnNewButton;
	private JButton btnCadastrar;
	private JLabel lblNewLabel;
	private JTextField txtProcurar;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox cbOrdenar;
	private JLabel setaOrdem;
	private int aux = 0;

	public TelaProcurar() {
		initComponents();
		this.setaOrdem.setIcon(new ImageIcon(getClass().getResource("setaCima.png")));
	}

	public void voltarTela() {
		TelaPrincipal.frame.setContentPane(new TelaInicial());
		TelaPrincipal.frame.setVisible(true);
	}

	public void procurarVeiculo() {
		AutomoveisDAO adao = new AutomoveisDAO();
		if(this.aux == 0) {
			preencheTabela(adao.procurar(txtProcurar.getText(), this.cbOrdenar.getSelectedItem().toString(), "asc"));			
		}else {
			preencheTabela(adao.procurar(txtProcurar.getText(), this.cbOrdenar.getSelectedItem().toString(), "desc"));
		}
	}

	public void preencheTabela(LinkedList<Automoveis> lista) {
		DefaultTableModel m = (DefaultTableModel) table.getModel();
		m.setRowCount(0);
		for (Automoveis a : lista) {
			m.addRow(new String[] { (a.getCodigo() + ""), a.getMarca(), a.getModelo(), (a.getAno() + "") });
		}
	}
	
	public void mudaOrdem() {
		if(this.aux == 0) {
			this.setaOrdem.setIcon(new ImageIcon(getClass().getResource("setaBaixo.png")));
			this.aux = 1;
		}else {
			this.setaOrdem.setIcon(new ImageIcon(getClass().getResource("setaCima.png")));
			this.aux = 0;
		}
	}

	public void alterarVeiculo() {
		int linha = this.table.getSelectedRow();
		Automoveis a1 = new Automoveis(Integer.parseInt(this.table.getValueAt(linha, 0) + ""),
				this.table.getValueAt(linha, 1) + "", this.table.getValueAt(linha, 2) + "",
				Integer.parseInt(this.table.getValueAt(linha, 3) + ""));
		TelaPrincipal.frame.setContentPane(new TelaAlterarExcluir(a1));
		TelaPrincipal.frame.setVisible(true);
	}

	private void initComponents() {
		setBounds(100, 100, 580, 380);
		setLayout(new MigLayout("", "[grow][grow][][grow][][][][][][][][][grow][][][grow]", "[][][][grow][][][][][][][][][][]"));

		this.btnNewButton = new JButton("     Voltar     ");
		this.btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarTela();
			}
		});

		this.lblNewLabel = new JLabel("<html>\r\n<h1>Procurar Ve\u00EDculo<h1>\r\n</html>");
		add(this.lblNewLabel, "cell 1 0 14 1,alignx center");

		this.txtProcurar = new JTextField();
		this.txtProcurar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.txtProcurar, "cell 1 1 7 1,growx");
		this.txtProcurar.setColumns(10);

		this.btnCadastrar = new JButton("Procurar");
		this.btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procurarVeiculo();
			}
		});
		
		this.lblNewLabel_2 = new JLabel("Ordenar:");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(this.lblNewLabel_2, "cell 8 1 3 1,alignx right");
		
		this.cbOrdenar = new JComboBox();
		this.cbOrdenar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"ID", "Marca", "Modelo", "Ano"}));
		add(this.cbOrdenar, "cell 11 1 3 1,growx");
		
		this.setaOrdem = new JLabel("");
		this.setaOrdem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudaOrdem();
			}
		});
		this.setaOrdem.setFont(new Font("Tahoma", Font.PLAIN, 5));
		add(this.setaOrdem, "cell 14 1");
		this.btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.btnCadastrar, "cell 1 2 14 1,growx");

		this.scrollPane = new JScrollPane();
		add(this.scrollPane, "cell 1 3 14 6,grow");

		this.table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alterarVeiculo();
			}
		});
		this.table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Marca", "Modelo", "Ano"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.table.getColumnModel().getColumn(0).setResizable(false);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(43);
		this.table.getColumnModel().getColumn(1).setResizable(false);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(141);
		this.table.getColumnModel().getColumn(2).setResizable(false);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(160);
		this.table.getColumnModel().getColumn(3).setResizable(false);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.scrollPane.setViewportView(this.table);

		this.lblNewLabel_1 = new JLabel("Para alterar ou excluir, clique sobre o ve\u00EDculo desejado.");
		add(this.lblNewLabel_1, "cell 1 9 14 1,alignx center");
		add(this.btnNewButton, "cell 1 12 14 1,growx");
	}

}
