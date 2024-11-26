package dao;

import factory.ConnectionFactory;

import java.sql.*;

public class AssociarProfessorDAO {

    public boolean associarProfessor(int professorId, int cursoId) {
        String sql = "UPDATE Curso SET professor_id = ? WHERE id = ?";

        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;
        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement associarProfessor = null;

        try {
            // Estabelece conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySql();
            // Prepara a query SQL
            associarProfessor = conn.prepareStatement(sql);

            associarProfessor.setInt(1, professorId);
            associarProfessor.setInt(2, cursoId);
            int rowsAffected = associarProfessor.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }

    public boolean removerProfessor(int cursoId) {
        String sql = "UPDATE Curso SET professor_id = NULL WHERE id = ?";
        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;
        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement associarProfessor = null;

        try {
            // Estabelece conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySql();
            // Prepara a query SQL
            associarProfessor = conn.prepareStatement(sql);

            associarProfessor.setInt(1, cursoId);
            int rowsAffected = associarProfessor.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }
    
    public boolean verificarAssociacaoProfessor(int cursoId) {
        String sql = "SELECT professor_id FROM Curso WHERE id = ?";
        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;
        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement associarProfessor = null;

        try {
            // Estabelece conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySql();
            // Prepara a query SQL
            associarProfessor = conn.prepareStatement(sql);

            ResultSet rs = associarProfessor.executeQuery();
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
