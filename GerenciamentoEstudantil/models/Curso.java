package models;

import dao.ProfessorDAO;
import models.Professor;

public class Curso {

    private int id;
    private String nomeCurso;
    private int cargaHoraria;
    private Professor professorResponsavel;

    public void exibirDados() {
        System.out.println("ID do Curso: " + (getId() > 0 ? getId() : "Sem registro"));
        System.out.println("Nome do Curso: " + (getNomeCurso() !=null ? getNomeCurso() : "Sem registro"));
        System.out.println("Carga Horária: " + getCargaHoraria()); // Caso não tenha idade, ele não irá mostrar
        System.out.println("Professor responsável: " + (getProfessorResponsavel() != null ? getProfessorResponsavel().getNome() : "Sem registro"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(Professor professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }
}