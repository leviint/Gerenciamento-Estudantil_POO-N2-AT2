package menus.professor;

import javax.swing.*;
import menus.MenuBase;

public class MenuProfessor extends MenuBase {

    public MenuProfessor(JFrame janelaPrincipal) {
        super("Menu Professor", janelaPrincipal);
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Menu Professor");
        configurarEstilo(label, subtitulo);

        adicionarBotao(new JButton("Cadastrar Professor"), 25, 50, 200, 30, 
            () -> { /* Implementar ação de cadastro */ });

        adicionarBotao(new JButton("Consultar Professor"), 25, 100, 200, 30, 
            () -> { /* Implementar ação de consulta */ });

        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }
}
