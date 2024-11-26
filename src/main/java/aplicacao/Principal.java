package aplicacao;

import dao.*;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Principal {
    public static void main(String[] args) {
        // Criação de objetos de Curso, Estudante e Professor
        Curso cursoMatematica = new Curso();
        cursoMatematica.setNomeCurso("Matemática fisica");
        cursoMatematica.setCargaHoraria(60); // Exemplo: 60 horas de carga horária

        Estudante estudanteMiguel = new Estudante();
        estudanteMiguel.setNome("Miguelll");
        estudanteMiguel.setIdade(20);
        estudanteMiguel.setMatricula("123456");

        Professor professorCarlos = new Professor();
        professorCarlos.setNome("Carloss");
        professorCarlos.setIdade(40);
        professorCarlos.setEspecialidade("Matemáticaaa");

        // Cadastro no banco de dados
        CursoDAO cursoDAO = new CursoDAO();
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        AssociarEstudanteDAO a1 = new AssociarEstudanteDAO();
        AssociarProfessorDAO a2 = new AssociarProfessorDAO();

        cursoDAO.cadastrarCurso(cursoMatematica);
        estudanteDAO.save(estudanteMiguel);
        professorDAO.save(professorCarlos);

        // Associação do estudante e professor ao curso
        a1.adicionarEstudanteNoCurso(estudanteMiguel.getId(), cursoMatematica.getId());
        a2.associarProfessor(professorCarlos.getId(), cursoMatematica.getId());

        RelatorioDAO relatorioDAO = new RelatorioDAO();

        // Relatório de Estudantes com seus Cursos
        List<Estudante> estudantes = relatorioDAO.listarEstudantesComCursos();
        System.out.println("Relatório de Estudantes e seus Cursos:");
        for (Estudante e : estudantes) {
            System.out.println("Estudante: " + e.getNome() + " | Idade: " + e.getIdade() + " | Matrícula: " + e.getMatricula());
            for (Curso c : e.getCursos()) {
                System.out.println("\tCurso: " + c.getNomeCurso() + " | Carga Horária: " + c.getCargaHoraria());
            }
        }

        // Relatório de Cursos com seus Professores
        List<Curso> cursos = relatorioDAO.listarCursosComProfessor();
        System.out.println("\nRelatório de Cursos e seus Professores:");
        for (Curso c : cursos) {
            System.out.println("Curso: " + c.getNomeCurso() + " | Carga Horária: " + c.getCargaHoraria());
            if (c.getProfessorResponsavel() != null) {
                System.out.println("\tProfessor: " + c.getProfessorResponsavel().getNome());
            } else {
                System.out.println("\tProfessor: Não atribuído");
            }
        }

    }
}