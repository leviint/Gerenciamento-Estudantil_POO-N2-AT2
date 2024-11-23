package dao;

import factory.ConnectionFactory;
import models.Estudante;

import java.sql.*;

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

            pstmPessoa = (PreparedStatement) conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS); // Atribui o objeto à conexão conn criada acima

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

    public void deleteByID(int id) {
        String sqlPessoa = "DELETE FROM pessoa WHERE id = ?";
        String sqlEstudante = "DELETE FROM estudante where id = ?";

        Connection conn = null;
        PreparedStatement pstmPessoa = null;
        PreparedStatement pstmEstudante = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstmEstudante = (PreparedStatement) conn.prepareStatement(sqlEstudante);

            pstmEstudante.setInt(1, id);

            pstmEstudante.execute();

            pstmPessoa = conn.prepareStatement(sqlPessoa);

            pstmPessoa.setInt(1, id);

            pstmPessoa.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmEstudante != null) {
                    pstmEstudante.close();
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
            System.out.println("Estudante deletado com sucesso!");
        }
    }

    public void update(Estudante estudante) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, idade = ? WHERE id = ?";
        String sqlEstudante = "UPDATE estudante SET matricula = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmPessoa = null;
        PreparedStatement pstmEstudante = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            conn.setAutoCommit(false);

            pstmPessoa = (PreparedStatement) conn.prepareStatement(sqlPessoa);

            pstmPessoa.setString(1, estudante.getNome());
            pstmPessoa.setInt(2, estudante.getIdade());
            pstmPessoa.setInt(3, estudante.getId());

            pstmPessoa.executeUpdate();

            pstmEstudante = (PreparedStatement) conn.prepareStatement(sqlEstudante);

            pstmEstudante.setString(1, estudante.getMatricula());
            pstmEstudante.setInt(2, estudante.getId());

            pstmEstudante.executeUpdate();

            conn.commit();

            System.out.println("Estudante atualizado com sucesso!");
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
                if (pstmEstudante != null) {
                    pstmEstudante.close();
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
