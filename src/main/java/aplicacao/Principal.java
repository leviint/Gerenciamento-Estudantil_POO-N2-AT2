package aplicacao;

import dao.EstudanteDAO;
import dao.ProfessorDAO;
import models.Estudante;
import models.Professor;
import menus.JanelaAluno;

public class Principal {
    public static void main(String[] args) {
        EstudanteDAO instanciae = new EstudanteDAO();
        ProfessorDAO instanciap = new ProfessorDAO();
        Estudante c1 = new Estudante();
        c1.setNome("Lucas");
        c1.setIdade(20);
        c1.setMatricula("UC24104222");

        instanciae.save(c1);

//        Professor p1 = new Professor();
//        p1.setNome("Marcos");
//        p1.setIdade(50);
//        p1.setEspecialidade("Compiladores");
//
//        instanciap.save(p1);
//
//        instanciap.deleteByID(3);

        int i = 1;
        for (Estudante e : instanciae.getEstudantes()) {
            System.out.println("------------------------------");
            System.out.printf("Estudante #: %d\n", i++);
            e.exibirDados();
            System.out.println("------------------------------\n");
        };



    }
}
