package menus;

import java.awt.Color;
import javax.swing.*;
import menus.helper_classes.*;

public class JanelaProfessor { //extends Janela 
    public static void main(String[] args) {

        JFrame frame = new JFrame("Professor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 420);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        JLabel tituloProfessor = new JLabel("Professor");
        tituloProfessor.setBounds(27, 14, 249, 45);
        tituloProfessor.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 30));
        tituloProfessor.setForeground(Color.decode("#D9D9D9"));
        panel.add(tituloProfessor);

        JButton cadastrarAluno = new JButton("Cadastrar");
        cadastrarAluno.setBounds(57, 299, 106, 29);
        cadastrarAluno.setBackground(Color.decode("#2e2e2e"));
        cadastrarAluno.setForeground(Color.decode("#D9D9D9"));
        cadastrarAluno.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        cadastrarAluno.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        cadastrarAluno.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(cadastrarAluno, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(cadastrarAluno);

        JButton consultarProfessor = new JButton("Consultar");
        consultarProfessor.setBounds(174, 297, 106, 29);
        consultarProfessor.setBackground(Color.decode("#2e2e2e"));
        consultarProfessor.setForeground(Color.decode("#D9D9D9"));
        consultarProfessor.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        consultarProfessor.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        consultarProfessor.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(consultarProfessor, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(consultarProfessor);

        JButton voltarAluno = new JButton("Voltar");
        voltarAluno.setBounds(276, 20, 50, 30);
        voltarAluno.setBackground(Color.decode("#2e2e2e"));
        voltarAluno.setForeground(Color.decode("#D9D9D9"));
        voltarAluno.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        voltarAluno.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        voltarAluno.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(voltarAluno, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(voltarAluno);

        JTextField campoNome = new JTextField("");
        campoNome.setBounds(54, 132, 120, 21);
        campoNome.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        campoNome.setBackground(Color.decode("#B2B2B2"));
        campoNome.setForeground(Color.decode("#656565"));
        campoNome.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
        OnFocusEventHelper.setOnFocusText(campoNome, "Insira o nome...", Color.decode("#353535"),   Color.decode("#656565"));
        panel.add(campoNome);

        JTextField campoEspecialidade = new JTextField("");
        campoEspecialidade.setBounds(55, 162, 120, 21);
        campoEspecialidade.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        campoEspecialidade.setBackground(Color.decode("#B2B2B2"));
        campoEspecialidade.setForeground(Color.decode("#656565"));
        campoEspecialidade.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
        OnFocusEventHelper.setOnFocusText(campoEspecialidade, "Insira a especialidade...", Color.decode("#353535"),   Color.decode("#656565"));
        panel.add(campoEspecialidade);

        frame.add(panel);
        frame.setVisible(true);
    }
}
