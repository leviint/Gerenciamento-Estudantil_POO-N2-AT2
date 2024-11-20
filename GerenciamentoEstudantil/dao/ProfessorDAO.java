package dao;

import factory.ConnectionFactory;
import models.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO {
    public void save(Professor professor) {
        String sqlPessoa = "INSERT INTO Pessoa(nome, idade, tipo) VALUES (?, ?, ?)";
        String sqlProfessor = "INSERT INTO Professor(id, especialidade) VALUES (?, ?)";

        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;

        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement pstmPessoa = null;
        PreparedStatement pstmProfessor = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstmPessoa = (PreparedStatement) conn.prepareStatement(sqlPessoa);
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
            throw new RuntimeException("Erro ao salvar estudante: " + e.getMessage());
        }
    }
}
