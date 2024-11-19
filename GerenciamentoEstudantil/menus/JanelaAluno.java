package menus;

import java.awt.Color;
import javax.swing.*;
import menus.helper_classes.*;

public class JanelaAluno { //extends Janela
    public static void main(String[] args) {

        JFrame frame = new JFrame("Aluno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 420);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        JLabel tituloAluno = new JLabel("Aluno");
        tituloAluno.setBounds(27, 14, 249, 45);
        tituloAluno.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 30));
        tituloAluno.setForeground(Color.decode("#D9D9D9"));
        panel.add(tituloAluno);

        JButton cadastrarAluno = new JButton("Cadastrar");
        cadastrarAluno.setBounds(57, 299, 106, 29);
        cadastrarAluno.setBackground(Color.decode("#2e2e2e"));
        cadastrarAluno.setForeground(Color.decode("#D9D9D9"));
        cadastrarAluno.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        cadastrarAluno.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        cadastrarAluno.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(cadastrarAluno, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(cadastrarAluno);

        JButton consultarAluno = new JButton("Consultar");
        consultarAluno.setBounds(174, 297, 106, 29);
        consultarAluno.setBackground(Color.decode("#2e2e2e"));
        consultarAluno.setForeground(Color.decode("#D9D9D9"));
        consultarAluno.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        consultarAluno.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        consultarAluno.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(consultarAluno, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(consultarAluno);

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

        JTextField campoIdade = new JTextField("");
        campoIdade.setBounds(55, 162, 120, 21);
        campoIdade.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        campoIdade.setBackground(Color.decode("#B2B2B2"));
        campoIdade.setForeground(Color.decode("#656565"));
        campoIdade.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
        OnFocusEventHelper.setOnFocusText(campoIdade, "Insira a idade...", Color.decode("#353535"),   Color.decode("#656565"));
        panel.add(campoIdade);

        JTextField campoMatricula = new JTextField("");
        campoMatricula.setBounds(55, 198, 120, 21);
        campoMatricula.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        campoMatricula.setBackground(Color.decode("#B2B2B2"));
        campoMatricula.setForeground(Color.decode("#656565"));
        campoMatricula.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
        OnFocusEventHelper.setOnFocusText(campoMatricula, "Insira a matrícula...", Color.decode("#353535"),   Color.decode("#656565"));
        panel.add(campoMatricula);

        frame.add(panel);
        frame.setVisible(true);
    }
}
