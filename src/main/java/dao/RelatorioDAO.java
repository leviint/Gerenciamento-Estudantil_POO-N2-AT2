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
        String sql = "SELECT e.id AS estudante_id, p.nome AS estudante_nome, p.idade AS estudante_idade, c.id AS curso_id, c.nomeCurso " +
                "FROM Estudante e " +
                "JOIN Pessoa p ON e.id = p.id " +
                "LEFT JOIN Matriculas m ON e.id = m.estudante_id " +
                "LEFT JOIN Curso c ON m.curso_id = c.id";


        List<Estudante> estudantes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {

            while (rst.next()) {
                int estudanteId = rst.getInt("estudante_id");
                String estudanteNome = rst.getString("estudante_nome");
                int estudanteIdade = rst.getInt("estudante_idade");

                Estudante estudante = new Estudante();
                estudante.setId(estudanteId);
                estudante.setNome(estudanteNome);
                estudante.setIdade(estudanteIdade);

                // Se o estudante estiver matriculado em algum curso
                if (rst.getObject("curso_id") != null) { // Verificação de NULL
                    Curso curso = new Curso();
                    curso.setId(rst.getInt("curso_id"));
                    curso.setNomeCurso(rst.getString("nomeCurso"));
                    estudante.addCurso(curso); // Associa o curso ao estudante
                }

                estudantes.add(estudante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Tratando exceções genéricas
        }

        return estudantes;
    }

    public List<Curso> listarCursosComProfessor() {
        String sql = "SELECT c.id AS curso_id, c.nomeCurso, c.cargaHoraria, p.id AS professor_id, p.nome AS professor_nome " +
                "FROM Curso c " +
                "LEFT JOIN Professor prof ON c.professor_id = prof.id " +
                "LEFT JOIN Pessoa p ON prof.id = p.id";

        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {

            while (rst.next()) {
                int cursoId = rst.getInt("curso_id");
                String cursoNome = rst.getString("nomeCurso");
                int cargaHoraria = rst.getInt("cargaHoraria");

                Curso curso = new Curso();
                curso.setId(cursoId);
                curso.setNomeCurso(cursoNome);
                curso.setCargaHoraria(cargaHoraria);

                // Se o curso tem um professor associado
                if (rst.getObject("professor_id") != null) { // Verificação de NULL
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
        }

        return cursos;
    }
}
