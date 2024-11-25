package menus.relatorio;

import javax.swing.*;
import menus.MenuBase;

public class MenuRelatorio extends MenuBase {

    public MenuRelatorio(JFrame janelaPrincipal) {
        super("Menu Relatório", janelaPrincipal);
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Menu Relatório");
        configurarEstilo(label, subtitulo);

        adicionarBotao(new JButton("Gerar Relatório de Estudantes"), 25, 50, 300, 30, 
            () -> { /* Implementar ação de gerar relatório */ });

        adicionarBotao(new JButton("Gerar Relatório de Professores"), 25, 100, 300, 30, 
            () -> { /* Implementar ação de gerar relatório */ });

        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }
}
