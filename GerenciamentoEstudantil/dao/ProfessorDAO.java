package dao;

import factory.ConnectionFactory;
import models.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    public void save(Professor professor) {
        String sqlPessoa = "INSERT INTO pessoa(nome, idade, tipo) VALUES (?, ?, ?)";
        String sqlProfessor = "INSERT INTO professor(id, especialidade) VALUES (?, ?)";

        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;

        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement pstmPessoa = null;
        PreparedStatement pstmProfessor = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstmPessoa = (PreparedStatement) conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            pstmPessoa.setString(1, professor.getNome());
            pstmPessoa.setInt(2, professor.getIdade());
            pstmPessoa.setString(3, "Professor");

            pstmPessoa.execute();

            ResultSet generatedId = pstmPessoa.getGeneratedKeys();

            if (generatedId.next()) {
                int idPessoa = generatedId.getInt(1);

                pstmProfessor = (PreparedStatement) conn.prepareStatement(sqlProfessor);
                pstmProfessor.setInt(1, idPessoa);
                pstmProfessor.setString(2, professor.getEspecialidade());
                pstmProfessor.execute();
            } else {
                throw new SQLException("Falha ao obter ID gerado por pessoa!");

            }
            System.out.println("Professor adicionado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar professor: " + e.getMessage());
        }
    }

    public void deleteByName(String nome) {
        String sqlPessoa = "DELETE FROM pessoa WHERE nome = ?";
        String sqlProfessor = "DELETE FROM professor WHERE id IN (SELECT id FROM pessoa WHERE nome = ?)";

        Connection conn = null;
        PreparedStatement pstmPessoa = null;
        PreparedStatement pstmProfessor = null;

        try {
            // Criação da conexão
            conn = ConnectionFactory.createConnectionToMySql();

            // Deleta da tabela estudante
            pstmProfessor = conn.prepareStatement(sqlProfessor);
            pstmProfessor.setString(1, nome);
            pstmProfessor.execute();

            // Deleta da tabela pessoa
            pstmPessoa = conn.prepareStatement(sqlPessoa);
            pstmPessoa.setString(1, nome);
            pstmPessoa.execute();

            System.out.println("Professor deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Fecha os recursos
                if (pstmProfessor != null) {
                    pstmProfessor.close();
                }
                if (pstmPessoa != null) {
                    pstmPessoa.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Professor professor) {
        String sql = "UPDATE pessoa INNER JOIN professor ON pessoa.id = professor.id " +
                "SET pessoa.idade = ?, professor.especialidade = ? " +
                "WHERE pessoa.nome = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, professor.getIdade());
            stmt.setString(2, professor.getEspecialidade());
            stmt.setString(3, professor.getNome()); // Nome usado para localizar o registro
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum registro foi atualizado. Verifique os dados fornecidos.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar professor: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Professor> getProfessor() {  /*Esse método retorna um Array List de Estudantes cadastrados no BD*/
        String sql = "SELECT pf.especialidade, p.nome, p.idade " +
                "FROM professor pf " +
                "INNER JOIN pessoa p ON pf.id = p.id";
        List<Professor> professores = new ArrayList<>();


        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rst = null; // Classe que irá recuperar os dados do banco ***SELECT***

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rst = pstm.executeQuery();

            while (rst.next()) {
                Professor pf = new Professor();

                pf.setEspecialidade(rst.getString("especialidade"));
                pf.setNome(rst.getString("nome"));
                pf.setIdade(rst.getInt("idade"));

                professores.add(pf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return professores;
    }
}
