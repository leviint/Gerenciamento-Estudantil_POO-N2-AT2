package menus.curso;

import java.awt.*;
import javax.swing.*;

public class MenuCurso {
    public static void exibirMenu(JFrame janelaPrincipal) {
        JFrame frame = new JFrame("Menu Curso");
        frame.setSize(400, 300);

        Point localizacao = janelaPrincipal.getLocation();
        frame.setLocation(localizacao.x, localizacao.y);

        // Painel
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(102, 113, 117));

        // Fonte
        Font fonte = new Font("Arial", Font.BOLD, 20);

        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel label2 = new JLabel("Menu Curso");
        JButton btnCadastrar = new JButton("Cadastrar Curso");
        JButton btnConsultar = new JButton("Consultar Curso");
        JButton btnSair = new JButton("Voltar");

        // Estilização
        label.setFont(fonte);
        label.setForeground(new Color(255,255,255));
        label2.setForeground(new Color(255,255,255));

        // Ação ao apertar os botões
        btnCadastrar.addActionListener(_ -> {
            
        });

        btnConsultar.addActionListener(_ -> {
            
        });

        // Posição dos botões - x, y, largura, altura
        label.setBounds(25, 0, 300, 30);
        label2.setBounds(25, 25, 175, 30);
        btnCadastrar.setBounds(25, 50, 200, 30);
        btnConsultar.setBounds(25, 100, 200, 30);
        btnSair.setBounds(250, 225, 75, 30);

        btnSair.addActionListener(_ -> frame.dispose());

        // Adicionar os botões ao menu
        painel.add(label);
        painel.add(label2);
        painel.add(btnCadastrar);
        painel.add(btnConsultar);
        painel.add(btnSair);

        frame.add(painel);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
