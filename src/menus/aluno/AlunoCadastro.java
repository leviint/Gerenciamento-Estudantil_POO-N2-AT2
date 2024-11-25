package menus.aluno;

import javax.swing.*;
import menus.MenuBase;

public class AlunoCadastro extends MenuBase{
    public AlunoCadastro(JFrame MenuAluno){
        super("Cadastrar Aluno", MenuAluno);
        construirMenu();
    }

    @Override
    protected void construirMenu(){
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Cadastrar Aluno");
        configurarEstilo(label, subtitulo);

        adicionarTexto("Insira o nome:", 25, 20, 200, 30, 14);
        adicionarCampo(new JTextField("Insira o nome..."), 25, 50, 200, 30, null);

        adicionarTexto("Insira a idade:", 25, 80, 200, 30, 14);
        adicionarCampo(new JTextField("Insira a idade..."), 25, 100, 200, 30, null);

        adicionarTexto("Insira a matrícula:", 25, 130, 200, 30, 14);
        adicionarCampo(new JTextField("Insira a matrícula..."), 25, 150, 200, 30, null);

        adicionarBotao(new JButton("Cadastrar"), 25, 200, 200, 30, 
            () -> { /* Implementar ação de cadastro */ });

        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }
}
