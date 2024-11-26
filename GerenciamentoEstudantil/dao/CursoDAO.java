package dao;

import factory.ConnectionFactory;
import models.Curso;
import models.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void save(Curso curso) {
        String sqlCurso = "INSERT INTO Curso(nomeCurso, cargaHoraria, professor_id) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement pstmCurso = conn.prepareStatement(sqlCurso, Statement.RETURN_GENERATED_KEYS)) {

            pstmCurso.setString(1, curso.getNomeCurso());
            pstmCurso.setInt(2, curso.getCargaHoraria());

            if (curso.getProfessorResponsavel() != null) {
                pstmCurso.setInt(3, curso.getProfessorResponsavel().getId());
            } else {
                pstmCurso.setNull(3, java.sql.Types.INTEGER);
            }

            pstmCurso.executeUpdate();

            try (ResultSet generatedId = pstmCurso.getGeneratedKeys()) {
                if (generatedId.next()) {
                    int idCurso = generatedId.getInt(1);
                    curso.setId(idCurso);
                    System.out.println("Curso adicionado com sucesso! ID: " + idCurso);
                } else {
                    throw new SQLException("Falha ao obter ID gerado por Curso");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar curso: " + e.getMessage(), e);
        }
    }

    public List<Curso> getCursos() {
        String sql = "SELECT c.id AS curso_id, c.nomeCurso, c.cargaHoraria, c.professor_id, "
                + "p.nome AS professor_nome, p.idade AS professor_idade "
                + "FROM Curso c "
                + "LEFT JOIN Pessoa p ON c.professor_id = p.id";

        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement pstmCurso = conn.prepareStatement(sql);
             ResultSet rst = pstmCurso.executeQuery()) {

            ProfessorDAO professorDAO = new ProfessorDAO();

            while (rst.next()) {
                Curso curso = new Curso();
                curso.setId(rst.getInt("curso_id"));
                curso.setNomeCurso(rst.getString("nomeCurso"));
                curso.setCargaHoraria(rst.getInt("cargaHoraria"));

                int professorId = rst.getInt("professor_id");
                if (professorId != 0) {
                    Professor professorResponsavel = professorDAO.getProfessorById(professorId);
                    curso.setProfessorResponsavel(professorResponsavel);
                }

                cursos.add(curso);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public Curso buscarCurso(String nomeCurso) {
        String sql = "SELECT * FROM Curso WHERE nomeCurso = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement pstmCurso = conn.prepareStatement(sql)) {

            pstmCurso.setString(1, nomeCurso);

            try (ResultSet rs = pstmCurso.executeQuery()) {
                if (rs.next()) {
                    Curso curso = new Curso();
                    curso.setId(rs.getInt("id"));
                    curso.setNomeCurso(rs.getString("nomeCurso"));
                    curso.setCargaHoraria(rs.getInt("cargaHoraria"));

                    int professorId = rs.getInt("professor_id");
                    if (professorId != 0) {
                        ProfessorDAO professorDAO = new ProfessorDAO();
                        Professor professor = professorDAO.getProfessorById(professorId);
                        curso.setProfessorResponsavel(professor);
                    }

                    return curso;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Curso curso) {
        String sql = "UPDATE Curso SET nomeCurso = ?, cargaHoraria = ?, professor_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement pstmCurso = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            pstmCurso.setString(1, curso.getNomeCurso());
            pstmCurso.setInt(2, curso.getCargaHoraria());

            if (curso.getProfessorResponsavel() != null) {
                pstmCurso.setInt(3, curso.getProfessorResponsavel().getId());
            } else {
                pstmCurso.setNull(3, java.sql.Types.INTEGER);
            }

            pstmCurso.setInt(4, curso.getId());
            pstmCurso.executeUpdate();

            conn.commit();
            System.out.println("Curso atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM Curso WHERE id = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement pstmCurso = conn.prepareStatement(sql)) {

            pstmCurso.setInt(1, id);

            int verificarId = pstmCurso.executeUpdate();
            if (verificarId > 0) {
                System.out.println("Curso excluÃ­do com sucesso!");
            } else {
                System.out.println("Nenhum curso encontrado com esse ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}