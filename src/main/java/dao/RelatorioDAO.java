package dao;

import factory.ConnectionFactory;
import dao.EstudanteDAO;
import dao.ProfessorDAO;
import dao.CursoDAO;
import models.Estudante;
import models.Professor;
import models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    public List<Estudante> listarEstudantesComCursos() {
        String sql = "SELECT e.id AS estudante_id, e.nome AS estudante_nome, e.idade AS estudante_idade, c.id AS curso_id, c.nomeCurso " +
                "FROM Estudante e " +
                "LEFT JOIN Matricula m ON e.id = m.estudante_id " +
                "LEFT JOIN Curso c ON m.curso_id = c.id";

        List<Estudante> estudantes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(sql);
            rst = pstm.executeQuery();

            while (rst.next()) {
                int estudanteId = rst.getInt("estudante_id");
                String estudanteNome = rst.getString("estudante_nome");
                int estudanteIdade = rst.getInt("estudante_idade");

                Estudante estudante = new Estudante();
                estudante.setId(estudanteId);
                estudante.setNome(estudanteNome);
                estudante.setIdade(estudanteIdade);

                // Se o estudante estiver matriculado em algum curso
                if (rst.getInt("curso_id") != 0) {
                    Curso curso = new Curso();
                    curso.setId(rst.getInt("curso_id"));
                    curso.setNomeCurso(rst.getString("nomeCurso"));

                    estudante.addCurso(curso); // Agora deve funcionar, adicionando o curso ao estudante
                }

                estudantes.add(estudante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
        } finally {
            try {
                if (rst != null) rst.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace(); // Tratando exceções genéricas
            }
        }

        return estudantes;
    }

    public List<Curso> listarCursosComProfessor() {
        String sql = "SELECT c.id AS curso_id, c.nomeCurso, c.cargaHoraria, p.id AS professor_id, p.nome AS professor_nome " +
                "FROM Curso c " +
                "LEFT JOIN Professor p ON c.professor_id = p.id";

        List<Curso> cursos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(sql);
            rst = pstm.executeQuery();

            while (rst.next()) {
                int cursoId = rst.getInt("curso_id");
                String cursoNome = rst.getString("nomeCurso");
                int cargaHoraria = rst.getInt("cargaHoraria");

                Curso curso = new Curso();
                curso.setId(cursoId);
                curso.setNomeCurso(cursoNome);
                curso.setCargaHoraria(cargaHoraria);

                // Se o curso tem um professor associado
                if (rst.getInt("professor_id") != 0) {
                    Professor professor = new Professor();
                    professor.setId(rst.getInt("professor_id"));
                    professor.setNome(rst.getString("professor_nome"));

                    curso.setProfessorResponsavel(professor); // Associa o professor ao curso
                }

                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
        } finally {
            try {
                if (rst != null) rst.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace(); // Tratando exceções genéricas
            }
        }

        return cursos;
    }
}