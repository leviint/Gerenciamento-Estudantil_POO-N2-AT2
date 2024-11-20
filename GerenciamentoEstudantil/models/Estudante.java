package models;

public class Estudante extends Pessoa {
    private String matricula;

    public void exibirDados() {
        System.out.println("Nome do estudante: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Matrícula do estudante: " + getMatricula());
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
