package br.com.devLopes.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.devLopes.model.Conexao;
import br.com.devLopes.model.entidades.Endereco;
import br.com.devLopes.model.entidades.Usuario;

public class DAOEndereco {

	Connection conexao;

	private Connection getConexao() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				return conexao;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexao = Conexao.gerarConexao();
		return conexao;
	}

	public boolean addEndereco(Usuario usuario, Endereco endereco) {

		Connection conexao = getConexao();

		String rua = endereco.getRua();
		String cidade = endereco.getCidade();
		String estado = endereco.getEstado();
		int id = usuario.getId();

		try {
			String sql = "INSERT INTO endereco(rua, cidade, estado, usuario_id) VALUES (?, ?, ?, ?)";
			PreparedStatement stm = conexao.prepareStatement(sql);

			stm.setString(1, rua);
			stm.setString(2, cidade);
			stm.setString(3, estado);
			stm.setInt(4, id);

			stm.execute();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	public Endereco getEnderecoPorEmail(String emailUser) {

		Connection conexao = getConexao();
		DAOUsuario DAOUser = new DAOUsuario();

		Usuario user = DAOUser.consultarPorEmail(emailUser);
		if (user == null) {
			return null;
		}

		int id = user.getId();

		String sql = "SELECT * FROM endereco WHERE usuario_id = ?";

		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);

			ResultSet resultado = stm.executeQuery();

			if (resultado.next()) {
				Endereco endereco = new Endereco();

				endereco.setId_usuario(resultado.getInt("usuario_id"));
				endereco.setCidade(resultado.getString("cidade"));
				endereco.setRua(resultado.getString("estado"));
				endereco.setEstado(resultado.getString("rua"));
				endereco.setId(resultado.getInt("id"));

				stm.close();
				conexao.close();
				return endereco;
			}

			stm.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public boolean alterarEndereco(Endereco endereco) {
		Connection conexao = getConexao();

		String rua = endereco.getRua();
		String cidade = endereco.getCidade();
		String estado = endereco.getEstado();
		int usuario_id = endereco.getId_usuario();

		try {
			String sql = "UPDATE endereco SET rua = ?, cidade = ?, estado = ? WHERE usuario_id = ?";
			PreparedStatement stm = conexao.prepareStatement(sql);

			stm.setString(1, rua);
			stm.setString(2, cidade);
			stm.setString(3, estado);
			stm.setInt(4, usuario_id);

			stm.execute();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	public void excluirEndereco(Usuario usuario) {
		try {
			int id = usuario.getId();
			Connection conexao = Conexao.gerarConexao();
			String sql = "DELETE FROM endereco WHERE usuario_id = ?";
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);
			stm.execute();

			stm.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "- Erro em Excluir usuario");
			e.printStackTrace();
		}
	}
}
