package dao;

import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssociarProfessorDAO {

    public boolean verificarProfessorExiste(int professorId) {
        String sql = "SELECT id FROM Professor WHERE id = ?";
        Connection conn = null;
        PreparedStatement verificarProfessor = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            verificarProfessor = conn.prepareStatement(sql);
            verificarProfessor.setInt(1, professorId);
            rs = verificarProfessor.executeQuery();
            return rs.next(); // Se o professor existir, rs.next() retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (verificarProfessor != null) verificarProfessor.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean associarProfessor(int professorId, int cursoId) {
        if (!verificarProfessorExiste(professorId)) {
            System.out.println("Professor não encontrado.");
            return false; // Retorna falso se o professor não existir
        }

        // Verificando se o curso já tem um professor associado
        String checkSql = "SELECT professor_id FROM Curso WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, cursoId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt("professor_id") != 0) {
                    System.out.println("Curso já tem um professor associado.");
                    return false; // Se já houver um professor, não faz a associação
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Associando o professor ao curso
        String sql = "INSERT INTO Associacoes (professor_id, curso_id) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement associarProfessor = conn.prepareStatement(sql)) {

            associarProfessor.setInt(1, professorId);
            associarProfessor.setInt(2, cursoId);
            int rowsAffected = associarProfessor.executeUpdate();
            return rowsAffected > 0; // Retorna true se a associação for bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean removerProfessor(int professorId, int cursoId) {
        String sql = "DELETE FROM associacoes WHERE professor_id = ? AND curso_id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, professorId);
            stmt.setInt(2, cursoId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Melhor adicionar um logger para mensagens de erro
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<String> gerarRelatorioProfessores() {
        List<String> relatorio = new ArrayList<>();

        // SQL ajustado para pegar os dados dos professores e os cursos associados através da tabela Associacoes
        String sql = "SELECT p.nome, GROUP_CONCAT(c.nomeCurso) as cursos " +
                "FROM pessoa p " +  // A tabela pessoa contém o nome
                "JOIN professor pr ON p.id = pr.id " + // Relacionamento entre pessoa e professor
                "JOIN associacoes a ON pr.id = a.professor_id " + // Relacionamento entre professor e associacao
                "JOIN curso c ON a.curso_id = c.id " + // Relacionamento entre associacao e curso
                "WHERE p.tipo = 'Professor' " + // Garantir que estamos pegando apenas professores
                "GROUP BY p.id";

        // Conectar ao banco e executar a consulta
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            // Processar o resultado da consulta
            while (rs.next()) {
                String nomeProfessor = rs.getString("nome");
                String cursos = rs.getString("cursos");

                // Adicionar os dados do professor e cursos à lista de relatório
                relatorio.add("Professor: " + nomeProfessor + ", Cursos: " + cursos);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Tratar erro de conexão ou consulta ao banco
            relatorio.add("Erro ao gerar relatório: " + e.getMessage());
        }

        // Retornar o relatório com os dados
        return relatorio;
    }
}