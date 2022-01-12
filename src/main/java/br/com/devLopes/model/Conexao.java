package br.com.devLopes.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

	private static Conexao instancia;

	private Conexao() {

	}

	public static Conexao getConexao() {
		if (instancia == null) {
			instancia = new Conexao();
			return instancia;
		} else {
			return instancia;
		}
	}

	public Connection gerarConexao() {
		try {
			Conexao getProps = new Conexao();

			Properties properties = getProps.getProperties();

			String url = properties.getProperty("banco.url");
			String user = properties.getProperty("banco.usuario");
			String senha = properties.getProperty("banco.senha");

			Connection conexao = DriverManager.getConnection(url, user, senha);
			return conexao;
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Properties getProperties() throws IOException {
		Properties props = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("../app.properties");
		props.load(in);
		return props;
	}
}
