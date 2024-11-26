package dao;

import factory.ConnectionFactory;

import java.sql.*;

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
            int validar = associarProfessor.executeUpdate();
            return validar > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
            return false;
        }
    }

}
