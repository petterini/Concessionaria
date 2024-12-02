import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class TelaCadastrar extends JPanel {
	private JButton btnNewButton;
	private JButton btnCadastrar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtAno;

	public TelaCadastrar() {
		initComponents();
	}

	public void voltarTela() {
		TelaPrincipal.frame.setContentPane(new TelaInicial());
		TelaPrincipal.frame.setVisible(true);
	}

	public void cadastrarVeiculo() {
		if (!txtAno.getText().matches("\\d{4}")) {
			JOptionPane.showMessageDialog(null, "Digite um ano válido!!");
		} else {
			if (!txtMarca.getText().equals("") && !txtModelo.getText().equals("") && !txtMarca.getText().equals("")) {
				AutomoveisDAO adao = new AutomoveisDAO();
				Automoveis a = new Automoveis(txtMarca.getText(), txtModelo.getText(),
						Integer.parseInt(txtAno.getText()));
				if (!adao.cadastrar(a)) {
					JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar veículo!!");
				}
				txtModelo.setText("");
				txtMarca.setText("");
				txtAno.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos!!");
			}
		}
	}

	private void initComponents() {
		setBounds(100, 100, 580, 380);
		setLayout(new MigLayout("", "[grow][][][grow][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][]"));

		this.btnNewButton = new JButton("     Voltar     ");
		this.btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarTela();
			}
		});

		this.lblNewLabel = new JLabel("<html>\r\n<h1>Cadastrar Ve\u00EDculo<h1>\r\n</html>");
		add(this.lblNewLabel, "cell 2 0 13 1,alignx center");

		this.lblNewLabel_1 = new JLabel("Marca");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.lblNewLabel_1, "cell 1 2 2 1");

		this.txtMarca = new JTextField();
		this.txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.txtMarca, "cell 3 2 11 1,growx");
		this.txtMarca.setColumns(10);

		this.lblNewLabel_2 = new JLabel("Modelo");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.lblNewLabel_2, "cell 1 3 2 1");

		this.txtModelo = new JTextField();
		this.txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.txtModelo.setColumns(10);
		add(this.txtModelo, "cell 3 3 11 1,growx");

		this.lblNewLabel_3 = new JLabel("Ano");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.lblNewLabel_3, "cell 1 4 2 1");

		this.txtAno = new JTextField();
		this.txtAno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.txtAno.setColumns(10);
		add(this.txtAno, "cell 3 4 11 1,growx");

		this.btnCadastrar = new JButton("     Cadastrar     ");
		this.btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarVeiculo();
			}
		});
		this.btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.btnCadastrar, "cell 2 9 12 1,growx");
		add(this.btnNewButton, "cell 2 11 12 1,growx");
	}

}
