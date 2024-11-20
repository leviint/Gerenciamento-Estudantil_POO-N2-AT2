package dao;

import factory.ConnectionFactory;
import models.Estudante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
Essas classes desse package são responsáveis por adaptar nossas classes Javas para se moldarem
 ao padrão de inclusões no MySQL
 */
public class EstudanteDAO {
    public void save(Estudante estudante /*Trás um objeto da classe Estudante como parâmetro*/) {
        String sqlPessoa = "INSERT INTO Pessoa(nome, idade, tipo) VALUES (?, ?, ?)"; // Os "?" funcionam como "placeholders" que serão passados no comando SQL
        String sqlEstudante = "INSERT INTO Estudante(id, matricula) VALUES (?, ?)";

        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;

        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement pstmPessoa = null; // Esse objetos serão responsáveis por colocar as informações nos placeholdes das inserções no BD
        PreparedStatement pstmEstudante = null;
        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstmPessoa = (PreparedStatement) conn.prepareStatement(sqlPessoa); // Atribui o objeto à conexão conn criada acima

            pstmPessoa.setString(1, estudante.getNome());
            pstmPessoa.setInt(2, estudante.getIdade());
            pstmPessoa.setObject(3, "Estudante");

            pstmPessoa.execute(); // Executa a String sqlPessoa no banco de dados

            ResultSet generatedId = pstmPessoa.getGeneratedKeys(); // Pega a chave primária gerada pelo Banco de dados e armazena na variável generatedId

            /*Caso o Id tenha sido gerado corretamente, ele vai fazer a próxima inserção na tabela Estudante*/
            if (generatedId.next()) {
                int idPessoa = generatedId.getInt(1);
                pstmEstudante = (PreparedStatement) conn.prepareStatement(sqlEstudante);

                pstmEstudante.setInt(1, idPessoa);
                pstmEstudante.setString(2, estudante.getMatricula());
                pstmEstudante.execute();

            } else {
                throw new SQLException("Falha ao obter ID gerado por Pessoa");
            }

            System.out.println("Estudante adicionado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar estudante: " + e.getMessage());
        }
    }
}
