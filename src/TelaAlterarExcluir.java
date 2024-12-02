import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class TelaAlterarExcluir extends JPanel {
	private JButton btnNewButton;
	private JButton btnAlterar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtAno;
	private JLabel lblNewLabel_4;
	private JTextField txtId;
	private JButton btnExcluir;
	private Automoveis auto;

	public TelaAlterarExcluir(Automoveis auto) {
		this.auto = auto;
		initComponents();
		preencheDados();
	}

	public void voltarTela() {
		TelaPrincipal.frame.setContentPane(new TelaProcurar());
		TelaPrincipal.frame.setVisible(true);
	}

	public void alterarVeiculo() {
		Automoveis a = new Automoveis(Integer.parseInt(this.txtId.getText()), this.txtMarca.getText(),
				this.txtModelo.getText(), Integer.parseInt(this.txtAno.getText()));
		AutomoveisDAO adao = new AutomoveisDAO();
		if (!txtAno.getText().matches("\\d{4}")) {
			JOptionPane.showMessageDialog(null, "Digite um ano válido!!");
		} else {
			if (!txtMarca.getText().equals("") && !txtModelo.getText().equals("") && !txtMarca.getText().equals("")) {
				if (!adao.alterar(a)) {
					JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso!!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao alterar veículo!!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos!!");
			}
		}
	}

	public void excluirVeiculo() {
		Automoveis a = new Automoveis(Integer.parseInt(this.txtId.getText()), this.txtMarca.getText(),
				this.txtModelo.getText(), Integer.parseInt(this.txtAno.getText()));
		AutomoveisDAO adao = new AutomoveisDAO();
		if (!adao.excluir(a)) {
			JOptionPane.showMessageDialog(null, "Veículo excluído com sucesso!!");
			voltarTela();
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao excluir veículo!!");
		}
	}

	public void preencheDados() {
		this.txtId.setText(this.auto.getCodigo() + "");
		this.txtMarca.setText(this.auto.getMarca());
		this.txtModelo.setText(this.auto.getModelo());
		this.txtAno.setText(this.auto.getAno() + "");
	}

	private void initComponents() {
		setBounds(100, 100, 580, 380);
		setLayout(new MigLayout("", "[grow][][][grow][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][]"));

		this.btnNewButton = new JButton("     Voltar     ");
		this.btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarTela();
			}
		});

		this.lblNewLabel = new JLabel("<html>\r\n<h1>Alterar/Excluir Ve\u00EDculo<h1>\r\n</html>");
		add(this.lblNewLabel, "cell 2 0 13 1,alignx center");

		this.lblNewLabel_4 = new JLabel("ID");
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.lblNewLabel_4, "cell 1 2 2 1");

		this.txtId = new JTextField();
		this.txtId.setEditable(false);
		this.txtId.setEnabled(false);
		this.txtId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.txtId.setColumns(10);
		add(this.txtId, "cell 3 2 11 1,growx");

		this.lblNewLabel_1 = new JLabel("Marca");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.lblNewLabel_1, "cell 1 3 2 1");

		this.txtMarca = new JTextField();
		this.txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.txtMarca, "cell 3 3 11 1,growx");
		this.txtMarca.setColumns(10);

		this.lblNewLabel_2 = new JLabel("Modelo");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.lblNewLabel_2, "cell 1 4 2 1");

		this.txtModelo = new JTextField();
		this.txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.txtModelo.setColumns(10);
		add(this.txtModelo, "cell 3 4 11 1,growx");

		this.lblNewLabel_3 = new JLabel("Ano");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.lblNewLabel_3, "cell 1 5 2 1");

		this.txtAno = new JTextField();
		this.txtAno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.txtAno.setColumns(10);
		add(this.txtAno, "cell 3 5 11 1,growx");

		this.btnAlterar = new JButton("     Alterar     ");
		this.btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarVeiculo();
			}
		});
		this.btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.btnAlterar, "cell 2 9 12 1,growx");

		this.btnExcluir = new JButton("     Excluir     ");
		this.btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirVeiculo();
			}
		});
		this.btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.btnExcluir, "cell 2 11 12 1,growx");
		add(this.btnNewButton, "cell 2 13 12 1,growx");
	}

}
