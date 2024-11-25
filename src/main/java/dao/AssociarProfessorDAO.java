package dao;

import factory.ConnectionFactory;

import java.sql.*;

public class AssociarProfessorDAO {

    // Método para associar um professor a um curso
    public boolean associarProfessor(int professorId, int cursoId) {
        String sql = "UPDATE Curso SET professor_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, professorId);
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

    // Método para remover a associação do professor com o curso
    public boolean removerProfessor(int cursoId) {
        String sql = "UPDATE Curso SET professor_id = NULL WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cursoId);
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

    // Método para verificar se o curso já tem um professor associado
    public boolean verificarAssociacaoProfessor(int cursoId) {
        String sql = "SELECT professor_id FROM Curso WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cursoId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt("professor_id") != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }
}
