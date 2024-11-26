package menus.aluno;

import javax.swing.*;
import menus.MenuBase;

public class MenuAluno extends MenuBase {

    public MenuAluno(JFrame janelaPrincipal) {
        super("Menu Aluno", janelaPrincipal);
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Menu Aluno");
        configurarEstilo(label, subtitulo);

        adicionarBotao(new JButton("Cadastrar Aluno"), 25, 50, 200, 30, 
            () -> new AlunoCadastro(frame).exibir());


        adicionarBotao(new JButton("Consultar Aluno"), 25, 100, 200, 30, 
            () -> new AlunoConsulta(frame).exibir());

        adicionarBotao(new JButton("Atualizar Aluno"), 25, 150, 200, 30,
                () -> new AlunoUpdate(frame).exibir());

        adicionarBotao(new JButton("Deletar Aluno"), 25, 200, 200, 30,
                () -> new AlunoDelete(frame).exibir());

        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }
}
