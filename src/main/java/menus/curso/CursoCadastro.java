package menus.curso;

import javax.swing.*;
import menus.MenuBase;

public class CursoCadastro extends MenuBase{
    public CursoCadastro(JFrame MenuCurso){
        super("Cadastrar Curso", MenuCurso);
        construirMenu();
    }

    @Override
    protected void construirMenu(){
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Cadastrar Curso");
        configurarEstilo(label, subtitulo);

        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        adicionarCampo(new JTextField("Insira o nome..."), 25, 80, 200, 30, null);

        adicionarTexto("Insira a carga horária, em horas:", 25, 110, 250, 30, 14);
        adicionarCampo(new JTextField("Insira a carga horária..."), 25, 140, 50, 30, null);

        adicionarBotao(new JButton("Cadastrar"), 250, 190, 95, 30, 
            () -> { /* Implementar ação de cadastro */ });

        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }
}
