package menus;

import java.awt.Color;
import javax.swing.*;
import menus.helper_classes.*;

public class JanelaPrincipal {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Gerenciamento Estudantil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        JLabel titulo = new JLabel("Gerenciamento Estudantil");
        titulo.setBounds(10, 7, 408, 52);
        titulo.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 30));
        titulo.setForeground(Color.decode("#D9D9D9"));
        panel.add(titulo);
   
        JLabel selecioneBotao = new JLabel("Selecione o que você quer gerenciar:");
        selecioneBotao.setBounds(11, 52, 300, 37);
        selecioneBotao.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        selecioneBotao.setForeground(Color.decode("#D9D9D9"));
        panel.add(selecioneBotao);
   
        JButton menuAluno = new JButton("Aluno");
        menuAluno.setBounds(20, 107, 106, 29);
        menuAluno.setBackground(Color.decode("#2e2e2e"));
        menuAluno.setForeground(Color.decode("#D9D9D9"));
        menuAluno.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        menuAluno.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        menuAluno.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(menuAluno, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(menuAluno);
   
        JButton menuProfessor = new JButton("models.Professor");
        menuProfessor.setBounds(20, 150, 106, 29);
        menuProfessor.setBackground(Color.decode("#2e2e2e"));
        menuProfessor.setForeground(Color.decode("#D9D9D9"));
        menuProfessor.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        menuProfessor.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        menuProfessor.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(menuProfessor, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(menuProfessor);
   
        JButton menuCurso = new JButton("Curso");
        menuCurso.setBounds(20, 190, 106, 29);
        menuCurso.setBackground(Color.decode("#2e2e2e"));
        menuCurso.setForeground(Color.decode("#D9D9D9"));
        menuCurso.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        menuCurso.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        menuCurso.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(menuCurso, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(menuCurso);
   
        JButton botaoSair = new JButton("Sair");
        botaoSair.setBounds(661, 10, 50, 30);
        botaoSair.setBackground(Color.decode("#2e2e2e"));
        botaoSair.setForeground(Color.decode("#D9D9D9"));
        botaoSair.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        botaoSair.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        botaoSair.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(botaoSair, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(botaoSair);
   
        frame.add(panel);
        frame.setVisible(true);
   
    }
}
