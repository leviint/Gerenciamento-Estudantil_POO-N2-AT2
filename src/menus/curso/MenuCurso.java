package menus.curso;

import javax.swing.*;
import menus.MenuBase;

public class MenuCurso extends MenuBase {

    public MenuCurso(JFrame janelaPrincipal) {
        super("Menu Curso", janelaPrincipal);
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Menu Professor");
        configurarEstilo(label, subtitulo);

        adicionarBotao(new JButton("Cadastrar Curso"), 25, 50, 200, 30, 
            () -> new CursoCadastro(frame).exibir());

        adicionarBotao(new JButton("Consultar Curso"), 25, 100, 200, 30, 
            () -> new CursoConsulta(frame).exibir());

        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }
}
