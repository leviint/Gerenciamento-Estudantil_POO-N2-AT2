package menus.relatorio;

import java.awt.*;
import javax.swing.*;

public class MenuRelatorio {
    public static void exibirMenu(JFrame janelaPrincipal) {
    JFrame frame = new JFrame("Relatório");
        frame.setSize(400, 300);

        // Define a posição da nova janela com base na posição da janela principal
        Point localizacao = janelaPrincipal.getLocation();
        frame.setLocation(localizacao.x, localizacao.y);

        // Painel
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(102, 113, 117));

        // Fonte
        Font fonte = new Font("Arial", Font.BOLD, 20);

        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel label2 = new JLabel("Gerar Relatórios");
        JButton btnRelatorioAluno = new JButton("Relatório de Estudantes");
        JButton btnRelatorioProfessor = new JButton("Relatório de Professores");
        JButton btnSair = new JButton("Voltar");

        // Estilização
        label.setFont(fonte);
        label.setForeground(new Color(255,255,255));
        label2.setForeground(new Color(255,255,255));

        // Ação ao apertar os botões
        btnRelatorioAluno.addActionListener(_ -> {
            // Implementar geração de relatório
        });

        btnRelatorioProfessor.addActionListener(_ -> {
            // Implementar geração de relatório
        });

        btnSair.addActionListener(_ -> frame.dispose());

        // Posição dos botões - x, y, largura, altura
        label.setBounds(25, 0, 300, 30);
        label2.setBounds(25, 25, 175, 30);
        btnRelatorioAluno.setBounds(25, 50, 200, 30);
        btnRelatorioProfessor.setBounds(25, 100, 200, 30);
        btnSair.setBounds(250, 225, 75, 30);

        // Adicionar os botões ao menu
        painel.add(label);
        painel.add(label2);
        painel.add(btnRelatorioAluno);
        painel.add(btnRelatorioProfessor);
        painel.add(btnSair);

        frame.add(painel);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
