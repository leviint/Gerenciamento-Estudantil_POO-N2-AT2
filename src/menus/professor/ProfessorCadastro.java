package menus.professor;

import javax.swing.*;
import menus.MenuBase;

public class ProfessorCadastro extends MenuBase{
    public ProfessorCadastro(JFrame MenuProfessor){
        super("Cadastrar Professor", MenuProfessor);
        construirMenu();
    }

    @Override
    protected void construirMenu(){
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Cadastrar Professor");
        configurarEstilo(label, subtitulo);

        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        adicionarCampo(new JTextField("Insira o nome..."), 25, 80, 200, 30, null);

        adicionarTexto("Insira a especialidade:", 25, 110, 200, 30, 14);
        adicionarCampo(new JTextField("Insira a especialidade..."), 25, 140, 200, 30, null);

        adicionarBotao(new JButton("Cadastrar"), 250, 190, 95, 30, 
            () -> { /* Implementar ação de cadastro */ });

        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }
}
