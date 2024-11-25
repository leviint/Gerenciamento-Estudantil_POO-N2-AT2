package dao;

import factory.ConnectionFactory;

import java.sql.*;

public class AssociarEstudanteDAO {

    // Método para associar um estudante a um curso
    public boolean adicionarEstudanteNoCurso(int estudanteId, int cursoId) {
        String sql = "INSERT INTO Matriculas (estudante_id, curso_id) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estudanteId);
            stmt.setInt(2, cursoId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }

    // Método para remover um estudante de um curso
    public boolean removerEstudanteDoCurso(int estudanteId, int cursoId) {
        String sql = "DELETE FROM Matriculas WHERE estudante_id = ? AND curso_id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estudanteId);
            stmt.setInt(2, cursoId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }

    // Método para verificar se um estudante já está matriculado no curso
    public boolean verificarMatricula(int estudanteId, int cursoId) {
        String sql = "SELECT * FROM Matriculas WHERE estudante_id = ? AND curso_id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estudanteId);
            stmt.setInt(2, cursoId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Se houver resultado, significa que a matrícula existe
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }
}