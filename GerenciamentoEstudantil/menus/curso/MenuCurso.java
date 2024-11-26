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
        JLabel label = new JLabel("Gerenciamento de Cursos");
        JLabel subtitulo = new JLabel("Escolha uma opção abaixo");
        configurarEstilo(label, subtitulo);

        // Botão para cadastrar curso
        adicionarBotao(new JButton("Cadastrar Curso"), 25, 50, 200, 30,
                () -> new CursoCadastro(frame).exibir());

        // Botão para consultar cursos
        adicionarBotao(new JButton("Consultar Cursos"), 25, 100, 200, 30,
                () -> new CursoConsulta(frame).exibir());

        // Botão para atualizar cursos
        adicionarBotao(new JButton("Atualizar Curso"), 25, 150, 200, 30,
                () -> new CursoUpdate(frame).exibir());

        // Botão para excluir cursos
        adicionarBotao(new JButton("Excluir Curso"), 25, 200, 200, 30,
                () -> new CursoDelete(frame).exibir());

        // Botão para voltar ao menu principal
        adicionarBotao(new JButton("Voltar"), 250, 250, 100, 30, this::fechar);
    }
}
