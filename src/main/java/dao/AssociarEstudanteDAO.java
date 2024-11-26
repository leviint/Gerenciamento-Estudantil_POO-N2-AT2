package dao;

import factory.ConnectionFactory;

import java.sql.*;

public class AssociarEstudanteDAO {

    public boolean adicionarEstudanteNoCurso(int estudanteId, int cursoId) {
        String sql = "INSERT INTO Matriculas (estudante_id, curso_id) VALUES (?, ?)";
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
            int rowsAffected = associarEstudante.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }

    public boolean removerEstudanteDoCurso(int estudanteId, int cursoId) {
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
            int rowsAffected = associarEstudante.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }

    public boolean verificarMatricula(int estudanteId, int cursoId) {
        String sql = "SELECT * FROM Matriculas WHERE estudante_id = ? AND curso_id = ?";
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
            ResultSet rs = associarEstudante.executeQuery();
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