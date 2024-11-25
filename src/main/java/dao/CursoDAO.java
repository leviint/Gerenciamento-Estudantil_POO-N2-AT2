package dao;

import factory.ConnectionFactory;
import models.Curso;
import models.Professor;
import dao.ProfessorDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CursoDAO {

    public void cadastrarCurso (Curso curso) {
        String sqlCurso = "INSERT INTO Curso(nomeCurso, cargaHoraria, professor_id) VALUES (?, ?, ?)";

        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;

        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement pstmCurso = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstmCurso = (PreparedStatement) conn.prepareStatement(sqlCurso, Statement.RETURN_GENERATED_KEYS);

            pstmCurso.setString(1, curso.getNomeCurso());
            pstmCurso.setInt(2, curso.getCargaHoraria());

            pstmCurso.execute(); // Executa a String sqlCurso no banco de dados

            ResultSet generatedId = pstmCurso.getGeneratedKeys(); // Pega a chave primária gerada pelo Banco de dados e armazena na variável generatedId

            /*Caso o Id tenha sido gerado corretamente, ele vai fazer a próxima inserção na tabela Estudante*/
            if (generatedId.next()) {
                int idCurso = generatedId.getInt(1);
                curso.setId(idCurso);
                System.out.println("Curso adicionado com sucesso! ID: " + idCurso);
            } else {
                throw new SQLException("Falha ao obter ID gerado por Curso");
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar curso: " + e.getMessage());
        } finally {
            try {
                if (pstmCurso != null) {
                    pstmCurso.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public List<Curso> listarCurso() {
        String sql = "SELECT c.id AS curso_id, c.nomeCurso, c.cargaHoraria, c.professor_id, "
                + "p.nome AS professor_nome, p.idade AS professor_idade "
                + "FROM Curso c "
                + "INNER JOIN Pessoa p ON c.professor_id = p.id "
                + "WHERE p.tipo = 'Professor'";
        List<Curso> cursos = new ArrayList<>();


        Connection conn = null;
        PreparedStatement pstmCurso = null;

        ResultSet rst = null; // Classe que irá recuperar os dados do banco ***SELECT***

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstmCurso = (PreparedStatement) conn.prepareStatement(sql);
            rst = pstmCurso.executeQuery();

            ProfessorDAO professorDAO = new ProfessorDAO();

            while (rst.next()) {
                int professorId = rst.getInt("professor_id");
                Professor professorResponsavel = professorDAO.getProfessorById(professorId);

                // Cria o curso e define os valores manualmente
                Curso curso = new Curso();
                curso.setId(rst.getInt("curso_id"));
                curso.setNomeCurso(rst.getString("nomeCurso"));
                curso.setCargaHoraria(rst.getInt("cargaHoraria"));
                curso.setProfessorResponsavel(professorResponsavel);

                // Adiciona o curso à lista de cursos
                cursos.add(curso);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pstmCurso != null) {
                    pstmCurso.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cursos;
    }

    public Curso buscarCurso(String nomeCurso) {
        String sql = "SELECT * FROM Curso WHERE nomeCurso = ?";

        // Cria um objeto do tipo ConnectionFactory inicialmente como nulo
        Connection conn = null;

        // Cria um objeto do tipo PreparedStatement inicialmente como nulos
        PreparedStatement pstmCurso = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstmCurso = conn.prepareStatement(sql);

            pstmCurso.setString( 1, nomeCurso);

            try (ResultSet rs = pstmCurso.executeQuery()) {
                if (rs.next()) {
                    // Cria o professor responsável
                    Professor professorResponsavel = new Professor();
                    professorResponsavel.setId(rs.getInt("professor_id"));
                    professorResponsavel.setNome(rs.getString("professor_nome"));
                    professorResponsavel.setIdade(rs.getInt("professor_idade"));

                    // Cria o curso e define os valores manualmente
                    Curso curso = new Curso();
                    curso.setId(rs.getInt("id"));
                    curso.setNomeCurso(rs.getString("nomeCurso"));
                    curso.setCargaHoraria(rs.getInt("cargaHoraria"));
                    curso.setProfessorResponsavel(professorResponsavel);

                    return curso;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
        return  null;
    }

    public void atualizarCurso (Curso curso) {
        String sql = "UPDATE Curso SET nomeCurso = ?, cargaHoraria = ?, professor_id = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmCurso = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstmCurso = (PreparedStatement) conn.prepareStatement(sql);

            // Desabilita o AutoCommit para garantir que a transação seja controlada manualmente
            conn.setAutoCommit(false);

            pstmCurso.setString(1, curso.getNomeCurso());
            pstmCurso.setInt(2, curso.getCargaHoraria());
            pstmCurso.setInt(3, curso.getProfessorResponsavel().getId());
            pstmCurso.setInt(4, curso.getId());

            // Executa o update
            pstmCurso.executeUpdate();

            // Confirma a transação
            conn.commit();

            System.out.println("Curso atualizado com sucesso!");
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
                if (pstmCurso != null) {
                    pstmCurso.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void excluirCurso(int id) {
        String sql = "DELETE FROM Curso WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmCurso = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstmCurso = conn.prepareStatement(sql);
            pstmCurso.setInt(1, id);

            int verificarId = pstmCurso.executeUpdate();

            if (verificarId > 0) {
                System.out.println("Curso excluído com sucesso!");
            } else {
                System.out.println("Nenhum curso encontrado com esse ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmCurso != null) {
                    pstmCurso.close();
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
