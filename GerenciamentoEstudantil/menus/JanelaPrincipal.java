package menus;

import java.awt.*;
import javax.swing.*;
import menus.aluno.MenuAluno;
import menus.curso.MenuCurso;
import menus.professor.MenuProfessor;
import menus.associacao.MenuAssociacao;
import menus.relatorio.MenuRelatorio;

public class JanelaPrincipal{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gerenciamento Estudantil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        frame.setLocation(700, 350);

        // Painel
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(102, 113, 117));

        // Fonte
        Font fonte = new Font("Arial", Font.BOLD, 20);

        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel label2 = new JLabel("Menu Principal");
        JButton btnAluno = new JButton("Gerenciar Alunos");
        JButton btnProfessor = new JButton("Gerenciar Professores");
        JButton btnCurso = new JButton("Gerenciar Cursos");
        JButton btnAssociacao = new JButton("Gerenciar Associações");
        JButton btnRelatorio = new JButton("Gerar Relatório");
        JButton btnSair = new JButton("Sair");

        // Estilização
        label.setFont(fonte);
        label.setForeground(new Color(255,255,255));
        label2.setForeground(new Color(255,255,255));

        // Ação ao apertar os botões
        btnAluno.addActionListener(_ -> new MenuAluno(frame).exibir());
        btnProfessor.addActionListener(_ -> new MenuProfessor(frame).exibir());
        btnCurso.addActionListener(_ -> new MenuCurso(frame).exibir());
        btnRelatorio.addActionListener(_ -> new MenuRelatorio(frame).exibir());
        btnAssociacao.addActionListener(_ -> new MenuAssociacao(frame).exibir());
        btnSair.addActionListener(_ -> System.exit(0));

        // Posição dos botões - x, y, largura, altura
        label.setBounds(25, 0, 300, 30);
        label2.setBounds(25, 35, 175, 30);
        btnAluno.setBounds(25, 70, 175, 30);
        btnProfessor.setBounds(25, 110, 175, 30);
        btnCurso.setBounds(25, 150, 175, 30);
        btnAssociacao.setBounds(25, 190, 175, 30); // Distância aumentada
        btnRelatorio.setBounds(25, 230, 175, 30);  // Distância aumentada
        btnSair.setBounds(250, 230, 75, 30);        // Distância aumentada

        // Adicionar os botões no menu
        painel.add(label);
        painel.add(label2);
        painel.add(btnAluno);
        painel.add(btnProfessor);
        painel.add(btnCurso);
        painel.add(btnRelatorio);
        painel.add(btnAssociacao);
        painel.add(btnSair);

        frame.add(painel);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
