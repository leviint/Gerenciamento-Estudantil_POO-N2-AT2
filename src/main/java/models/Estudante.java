package models;

import java.util.ArrayList;
import java.util.List;

public class Estudante extends Pessoa {
    private String matricula;

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
    
}
