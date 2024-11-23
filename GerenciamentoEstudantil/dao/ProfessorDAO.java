package dao;

import factory.ConnectionFactory;
import models.Estudante;
import models.Professor;

import java.sql.*;

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

    public void deleteByID(int id) {
        String sqlPessoa = "DELETE FROM pessoa WHERE id = ?";
        String sqlProfessor = "DELETE FROM professor where id = ?";

        Connection conn = null;
        PreparedStatement pstmPessoa = null;
        PreparedStatement pstmProfessor = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstmProfessor = (PreparedStatement) conn.prepareStatement(sqlProfessor);

            pstmProfessor.setInt(1, id);

            pstmProfessor.execute();

            pstmPessoa = conn.prepareStatement(sqlPessoa);

            pstmPessoa.setInt(1, id);

            pstmPessoa.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
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
            System.out.println("Professor deletado com sucesso!");
        }
    }

    public void update(Professor professor) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, idade = ? WHERE id = ?";
        String sqlProfessor = "UPDATE professor SET especialidade = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmPessoa = null;
        PreparedStatement pstmProfessor = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            conn.setAutoCommit(false);

            pstmPessoa = (PreparedStatement) conn.prepareStatement(sqlPessoa);

            pstmPessoa.setString(1, professor.getNome());
            pstmPessoa.setInt(2, professor.getIdade());
            pstmPessoa.setInt(3, professor.getId());

            pstmPessoa.executeUpdate();

            pstmProfessor = (PreparedStatement) conn.prepareStatement(sqlProfessor);

            pstmProfessor.setString(1, professor.getEspecialidade());
            pstmProfessor.setInt(2, professor.getId());

            pstmProfessor.executeUpdate();

            conn.commit();

            System.out.println("Professor atualizado com sucesso!");
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmPessoa != null) {
                    pstmPessoa.close();
                }
                if (pstmProfessor != null) {
                    pstmProfessor.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
