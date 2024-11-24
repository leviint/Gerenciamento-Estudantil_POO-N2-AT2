package models;

/*
Movi essas classes para esse package para que pudessem ser usadas em outros packages
 */

public abstract class Pessoa {
    protected int id;
    protected String nome;
    protected int idade;

    public abstract void exibirDados();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
