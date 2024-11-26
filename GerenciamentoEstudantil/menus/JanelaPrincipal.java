package menus;

import java.awt.*;
import javax.swing.*;
import menus.aluno.MenuAluno;
import menus.curso.MenuCurso;
import menus.professor.MenuProfessor;
import menus.relatorio.MenuRelatorio;

public class JanelaPrincipal {
    public static void main(String[] args) {
        // Configuração da janela principal
        JFrame frame = new JFrame("Gerenciamento Estudantil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Janela maior para comportar elementos
        frame.setLocation(700, 350);
        frame.setResizable(false);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(45, 52, 54));
        frame.add(painel);

        // Títulos estilizados
        JLabel titulo = new JLabel("Gerenciamento Estudantil", SwingConstants.CENTER);
        JLabel subtitulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 26);
        Font fonteSubtitulo = new Font("Arial", Font.PLAIN, 18);
        titulo.setFont(fonteTitulo);
        titulo.setForeground(Color.WHITE);
        subtitulo.setFont(fonteSubtitulo);
        subtitulo.setForeground(new Color(200, 200, 200));

        // Configuração de botões com estilos diferenciados
        JButton btnAluno = criarBotao("Gerenciar Alunos", new Color(41, 128, 185));
        JButton btnProfessor = criarBotao("Gerenciar Professores", new Color(41, 128, 185));
        JButton btnCurso = criarBotao("Gerenciar Cursos", new Color(41, 128, 185));
        JButton btnRelatorio = criarBotao("Gerar Relatório", new Color(41, 128, 185));
        JButton btnSair = criarBotao("Sair", new Color(231, 76, 60));

        // Ação ao apertar os botões
        btnAluno.addActionListener(_ -> new MenuAluno(frame).exibir());
        btnProfessor.addActionListener(_ -> new MenuProfessor(frame).exibir());
        btnCurso.addActionListener(_ -> new MenuCurso(frame).exibir());
        btnRelatorio.addActionListener(_ -> new MenuRelatorio(frame).exibir());
        btnSair.addActionListener(_ -> System.exit(0));

        // Configuração de posições e tamanhos
        titulo.setBounds(50, 20, 400, 40);
        subtitulo.setBounds(50, 60, 400, 30);
        btnAluno.setBounds(150, 100, 200, 40);
        btnProfessor.setBounds(150, 150, 200, 40);
        btnCurso.setBounds(150, 200, 200, 40);
        btnRelatorio.setBounds(150, 250, 200, 40);
        btnSair.setBounds(190, 310, 120, 40);

        // Adicionando componentes ao painel
        painel.add(titulo);
        painel.add(subtitulo);
        painel.add(btnAluno);
        painel.add(btnProfessor);
        painel.add(btnCurso);
        painel.add(btnRelatorio);
        painel.add(btnSair);

        // Exibindo a janela
        frame.setVisible(true);
    }

    // Método utilitário para criar botões estilizados
    private static JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder());
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito de hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });

        return botao;
    }
}
