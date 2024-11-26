package dao;

import factory.ConnectionFactory;
import models.Estudante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        }
    }

    public void deleteByName(String nome) {
        String sqlPessoa = "DELETE FROM pessoa WHERE nome = ?";
        String sqlEstudante = "DELETE FROM estudante WHERE id IN (SELECT id FROM pessoa WHERE nome = ?)";

        Connection conn = null;
        PreparedStatement pstmPessoa = null;
        PreparedStatement pstmEstudante = null;

        try {
            // Criação da conexão
            conn = ConnectionFactory.createConnectionToMySql();

            // Deleta da tabela estudante
            pstmEstudante = conn.prepareStatement(sqlEstudante);
            pstmEstudante.setString(1, nome);
            pstmEstudante.execute();

            // Deleta da tabela pessoa
            pstmPessoa = conn.prepareStatement(sqlPessoa);
            pstmPessoa.setString(1, nome);
            pstmPessoa.execute();

            System.out.println("Estudante deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Fecha os recursos
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
        }
    }

    public void update(Estudante estudante) throws SQLException {
        String sql = "UPDATE pessoa INNER JOIN estudante ON pessoa.id = estudante.id " +
                "SET pessoa.idade = ?, estudante.matricula = ? " +
                "WHERE pessoa.nome = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estudante.getIdade());
            stmt.setString(2, estudante.getMatricula());
            stmt.setString(3, estudante.getNome()); // Nome usado para localizar o registro
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum registro foi atualizado. Verifique os dados fornecidos.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar estudante: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Estudante> getEstudantes() {  /*Esse método retorna um Array List de Estudantes cadastrados no BD*/
        String sql = "SELECT e.matricula, p.nome, p.idade " +
                "FROM estudante e " +
                "INNER JOIN pessoa p ON e.id = p.id";
        List<Estudante> estudantes = new ArrayList<>();


        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rst = null; // Classe que irá recuperar os dados do banco ***SELECT***

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rst = pstm.executeQuery();

            while (rst.next()) {
                Estudante e = new Estudante();

                e.setMatricula(rst.getString("matricula"));
                e.setNome(rst.getString("nome"));
                e.setIdade(rst.getInt("idade"));

                estudantes.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return estudantes;
    }

    public List<String> obterRelatorioEstudantes() {
        List<String> relatorio = new ArrayList<>();
        String sql = "SELECT e.nome, c.nome FROM estudante e " +
                "INNER JOIN curso_estudante ce ON e.id = ce.estudante_id " +
                "INNER JOIN curso c ON ce.curso_id = c.id";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
                Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomeEstudante = rs.getString("e.nome");
                String nomeCurso = rs.getString("c.nome");
                relatorio.add(nomeEstudante + " está matriculado no curso: " + nomeCurso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return relatorio;
    }
}
