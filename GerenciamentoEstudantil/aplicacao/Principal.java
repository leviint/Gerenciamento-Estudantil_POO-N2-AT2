package aplicacao;

import dao.EstudanteDAO;
import models.Estudante;

public class Principal {
    public static void main(String[] args) {
        EstudanteDAO instancia = new EstudanteDAO();
        Estudante c1 = new Estudante();
        c1.setNome("Victor");
        c1.setIdade(23);
        c1.setMatricula("UC24103366");

        instancia.save(c1);
    }
}
