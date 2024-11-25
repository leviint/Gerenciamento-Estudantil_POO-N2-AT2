package menus;

import java.awt.*;
import javax.swing.*;
import menus.aluno.MenuAluno;
import menus.curso.MenuCurso;
import menus.professor.MenuProfessor;
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
        JButton btnRelatorio = new JButton("Gerar Relatório");
        JButton btnSair = new JButton("Sair");

        // Estilização
        label.setFont(fonte);
        label.setForeground(new Color(255,255,255));
        label2.setForeground(new Color(255,255,255));

        // Ação ao apertar os botões
        btnAluno.addActionListener(_ -> new MenuAluno(frame).exibir());
        btnProfessor.addActionListener(_ -> new MenuProfessor(frame).exibir());
        btnCurso.addActionListener(_ -> MenuCurso.exibirMenu(frame));
        btnRelatorio.addActionListener(_ -> MenuRelatorio.exibirMenu(frame));
        btnSair.addActionListener(_ -> System.exit(0));


        // Posição dos botões - x, y, largura, altura
        label.setBounds(25, 0, 300, 30);
        label2.setBounds(25, 25, 175, 30);
        btnAluno.setBounds(25, 50, 175, 30);
        btnProfessor.setBounds(25, 100, 175, 30);
        btnCurso.setBounds(25, 150, 175, 30);
        btnRelatorio.setBounds(25, 200, 175, 30);
        btnSair.setBounds(250, 225, 75, 30);

        // Adicionar os botões no menu
        painel.add(label);
        painel.add(label2);
        painel.add(btnAluno);
        painel.add(btnProfessor);
        painel.add(btnCurso);
        painel.add(btnRelatorio);
        painel.add(btnSair);

        frame.add(painel);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}

