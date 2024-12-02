import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class AutomoveisDAO {
	private Connection conexao;

	public AutomoveisDAO() {
		this.conexao = Conexao.getConexao();
	}

	public boolean cadastrar(Automoveis a) {
		String sql = "INSERT INTO automoveis (marca, modelo, ano) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql);
			ps.setString(1, a.getMarca());
			ps.setString(2, a.getModelo());
			ps.setInt(3, a.getAno());
			boolean retorno = ps.execute();
			ps.close();
			return retorno;
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar ve�culo.");
			e.printStackTrace();
			return true;
		}
	}

	public boolean alterar(Automoveis a) {
		String sql = "UPDATE automoveis set marca = ?, modelo = ?, ano = ? WHERE id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql);
			ps.setString(1, a.getMarca());
			ps.setString(2, a.getModelo());
			ps.setInt(3, a.getAno());
			ps.setInt(4, a.getCodigo());
			boolean retorno = ps.execute();
			ps.close();
			return retorno;
		} catch (Exception e) {
			System.out.println("Erro ao alterar ve�culo.");
			e.printStackTrace();
			return true;
		}
	}

	public boolean excluir(Automoveis a) {
		String sql = "DELETE FROM automoveis WHERE id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql);
			ps.setInt(1, a.getCodigo());
			boolean retorno = ps.execute();
			ps.close();
			return retorno;
		} catch (Exception e) {
			System.out.println("Erro ao excluir ve�culo.");
			e.printStackTrace();
			return true;
		}
	}

	public LinkedList<Automoveis> procurar(String texto, String ordem, String sentido) {
		String sql = "SELECT * FROM automoveis WHERE modelo ILIKE ? or marca ILIKE ? or ano = ? ORDER BY " + ordem + " " + sentido;
		LinkedList<Automoveis> lista = new LinkedList<>();
		int ano = -1;
		if(texto.matches("\\d{4}")) {
			ano = Integer.parseInt(texto);
		}
		try {
			PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql);
			ps.setString(1, '%' + texto + '%');
			ps.setString(2, '%' + texto + '%');
			ps.setInt(3, ano);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Automoveis a = new Automoveis(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("ano"));
				lista.add(a);
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
