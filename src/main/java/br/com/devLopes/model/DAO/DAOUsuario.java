package br.com.devLopes.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.devLopes.model.Conexao;
import br.com.devLopes.model.entidades.Usuario;

public class DAOUsuario {

	DAOEndereco daoEndereco;

	private Connection conexao;

	private Connection getConexao() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				return conexao;
			}
		} catch (SQLException e) {

		}
		conexao = Conexao.getConexao().gerarConexao();
		return conexao;
	}

	public void cadastrarUsuario(Usuario user) {

		conexao = getConexao();

		String nome = user.getNome();
		String email = user.getEmail();
		String senha = user.getSenha();

		String sql = "INSERT INTO usuario(nome, email, senha) VALUES (?, ?, ?)";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);

			stm.setString(1, nome);
			stm.setString(2, email);
			stm.setString(3, senha);

			stm.execute();

			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Usuario consultarPorEmail(String email) {
		conexao = getConexao();
		String sql = "SELECT * FROM usuario WHERE email = ?";
		try {

			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, email);

			ResultSet resultado = stm.executeQuery();

			if (resultado.next()) {

				int resultId = resultado.getInt("id");
				String resultNome = resultado.getString("nome");
				String resultEmail = resultado.getString("email");
				String resultSenha = resultado.getString("senha");

				Usuario user = new Usuario();
				user.setId(resultId);
				user.setNome(resultNome);
				user.setEmail(resultEmail);
				user.setSenha(resultSenha);

				stm.close();
				conexao.close();
				return user;
			}

			stm.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void excluirUsuario(Usuario user) {
		conexao = getConexao();
		daoEndereco = new DAOEndereco();
		try {
			int id = user.getId();

			daoEndereco.excluirEndereco(user);

			String sql = "DELETE FROM usuario WHERE id = ?";
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
