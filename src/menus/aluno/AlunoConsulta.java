package menus.aluno;

import javax.swing.*;
import menus.MenuBase;

public class AlunoConsulta extends MenuBase{
    public AlunoConsulta(JFrame MenuAluno){
        super("Consultar Aluno", MenuAluno);
        construirMenu();
    }

    @Override
    protected void construirMenu(){
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Consultar Aluno");
        configurarEstilo(label, subtitulo);

        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        adicionarCampo(new JTextField("Insira o nome..."), 25, 80, 200, 30, null);

        adicionarBotao(new JButton("Consultar"), 25, 120, 200, 30,
            () -> {});

        adicionarBotao(new JButton("Editar"), 250, 150, 95, 30, 
            () -> {});

        adicionarBotao(new JButton("Excluir"), 250, 190, 95, 30, 
            () -> {});

        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }
}
