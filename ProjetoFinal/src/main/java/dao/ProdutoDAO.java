package dao;

import database.Conexao;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {

    // BUSCAR PRODUTO POR ID
    public Produto buscarPorId(int id) {

        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                Produto produto = new Produto();

                produto.setId(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setPreco(resultado.getDouble("preco"));

                return produto;
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }

        return null;
    }

    // BUSCAR PRODUTO POR NOME
    public Produto buscarPorNome(String nome) {

        String sql = "SELECT * FROM produto WHERE nome LIKE ? LIMIT 1";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                Produto produto = new Produto();

                produto.setId(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setPreco(resultado.getDouble("preco"));

                return produto;
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }

        return null;
    }
}