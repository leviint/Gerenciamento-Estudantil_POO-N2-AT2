package models;

import java.util.ArrayList;
import java.util.List;

public class Estudante extends Pessoa {
    private String matricula;
    private List<Curso> cursos = new ArrayList<>();

    public void exibirDados() {
        System.out.println("Nome do estudante: " + (getNome() !=null ? getNome() : "Sem registro"));
        System.out.println("Idade: " + getIdade()); // Caso não tenha idade, ele não irá mostrar
        System.out.println("Matrícula do estudante: " + (getMatricula() != null ? getMatricula() : "Sem registro"));
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    // Método para adicionar curso à lista
    public void addCurso(Curso curso) {
        this.cursos.add(curso);
    }

}
