package models;

public class Professor extends Pessoa {
    private String especialidade;

    public void exibirDados() {
        System.out.println("Nome do Professor: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Especialidade em: " + getEspecialidade());
    }

    public void setEspecialidade (String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}
