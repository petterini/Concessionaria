import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaInicial extends JPanel {
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JButton btnProcurarVeculo;
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */
	public TelaInicial() {

		initComponents();
	}
	
	public void trocaTelaCadastrar() {
		TelaPrincipal.frame.setContentPane(new TelaCadastrar());
		TelaPrincipal.frame.setVisible(true);
	}
	
	public void trocaTelaProcurar() {
		TelaPrincipal.frame.setContentPane(new TelaProcurar());
		TelaPrincipal.frame.setVisible(true);
	}
	
	private void initComponents() {
		setLayout(new MigLayout("", "[grow][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][]"));
		
		this.lblNewLabel = new JLabel("<html>\r\n<h1>Concession\u00E1ria ADS 19<h1>\r\n</html>");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(this.lblNewLabel, "cell 1 0 11 1,alignx center");
		
		this.btnNewButton = new JButton("<html>\r\n<h2>Cadastrar Ve\u00EDculo<h2>\r\n</html>");
		this.btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocaTelaCadastrar();
			}
		});
		add(this.btnNewButton, "cell 2 3 10 3,grow");
		
		this.lblNewLabel_1 = new JLabel("<html>\r\n\t<br>\r\n\t<br>\r\n</html>");
		add(this.lblNewLabel_1, "cell 6 6");
		
		this.btnProcurarVeculo = new JButton("<html>\r\n<h2>Procurar/Editar/Excluir Ve\u00EDculo<h2>\r\n</html>");
		this.btnProcurarVeculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocaTelaProcurar();
			}
		});
		this.btnProcurarVeculo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(this.btnProcurarVeculo, "cell 2 8 10 3,grow");
	}

}
