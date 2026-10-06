package dao;

import database.Conexao;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    // CADASTRAR USUÁRIO
    public boolean cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome, sobrenome, login, senha) VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getLogin());
            stmt.setString(4, usuario.getSenha());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());

            return false;
        }
    }

    // LOGIN
    public Usuario login(String login, String senha) {

        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            var resultado = stmt.executeQuery();

            if (resultado.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(resultado.getInt("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setSobrenome(resultado.getString("sobrenome"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setTipo(resultado.getString("tipo"));

                return usuario;
            }

        } catch (SQLException e) {

            System.out.println("Erro ao realizar login: " + e.getMessage());
        }

        return null;
    }
}