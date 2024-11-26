package dao;

import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssociarEstudanteDAO {

    public boolean verificarEstudanteExiste(int estudanteId) {
        String sql = "SELECT id FROM Estudante WHERE id = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement verificarEstudante = conn.prepareStatement(sql)) {

            verificarEstudante.setInt(1, estudanteId);
            try (ResultSet rs = verificarEstudante.executeQuery()) {
                return rs.next(); // Se o estudante existir, rs.next() retorna true
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean associarEstudante(int estudanteId, int cursoId) {
        if (!verificarEstudanteExiste(estudanteId)) {
            System.out.println("Estudante não encontrado.");
            return false; // Retorna falso se o estudante não existir
        }

        String sql = "INSERT INTO Matriculas (estudante_id, curso_id) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement associarEstudante = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            associarEstudante = conn.prepareStatement(sql);
            associarEstudante.setInt(1, estudanteId);
            associarEstudante.setInt(2, cursoId);
            int validar = associarEstudante.executeUpdate();
            return validar > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removerEstudante(int estudanteId, int cursoId) {
        String sql = "DELETE FROM Matriculas WHERE estudante_id = ? AND curso_id = ?";
        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;
        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement associarEstudante = null;

        try {
            // Estabelece conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySql();
            // Prepara a query SQL
            associarEstudante = conn.prepareStatement(sql);
            associarEstudante.setInt(1, estudanteId);
            associarEstudante.setInt(2, cursoId);
            int validar = associarEstudante.executeUpdate();
            return validar > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }

    public List<String> gerarRelatorioEstudantes() {
        List<String> relatorio = new ArrayList<>();

        String sql = "SELECT p.nome, GROUP_CONCAT(c.nomeCurso) as cursos " +
                "FROM pessoa p " +
                "JOIN estudante e ON p.id = e.id " + // Relacionamento entre pessoa e estudante
                "JOIN matriculas m ON e.id = m.estudante_id " + // Relacionamento entre estudante e matriculas
                "JOIN curso c ON m.curso_id = c.id " + // Relacionamento entre matriculas e curso
                "WHERE p.tipo = 'Estudante' " + // Garantir que estamos pegando apenas estudantes
                "GROUP BY p.id";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            // Processar o resultado da consulta
            while (rs.next()) {
                String nomeEstudante = rs.getString("nome");
                String cursos = rs.getString("cursos");

                // Adicionar os dados do estudante e cursos à lista de relatório
                relatorio.add("Estudante: " + nomeEstudante + ", Cursos: " + cursos);
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
