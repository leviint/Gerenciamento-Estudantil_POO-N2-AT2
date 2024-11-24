package menus;

import java.awt.Color;
import javax.swing.*;
import menus.helper_classes.*;

public class JanelaCurso { //extends Janela
    public static void main(String[] args) {

        JFrame frame = new JFrame("Curso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 420);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        JLabel tituloCurso = new JLabel("Curso");
        tituloCurso.setBounds(27, 14, 249, 45);
        tituloCurso.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 30));
        tituloCurso.setForeground(Color.decode("#D9D9D9"));
        panel.add(tituloCurso);

        JButton cadastrarCurso = new JButton("Cadastrar");
        cadastrarCurso.setBounds(57, 299, 106, 29);
        cadastrarCurso.setBackground(Color.decode("#2e2e2e"));
        cadastrarCurso.setForeground(Color.decode("#D9D9D9"));
        cadastrarCurso.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        cadastrarCurso.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        cadastrarCurso.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(cadastrarCurso, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(cadastrarCurso);

        JButton consultarCurso = new JButton("Consultar");
        consultarCurso.setBounds(174, 297, 106, 29);
        consultarCurso.setBackground(Color.decode("#2e2e2e"));
        consultarCurso.setForeground(Color.decode("#D9D9D9"));
        consultarCurso.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        consultarCurso.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        consultarCurso.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(consultarCurso, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(consultarCurso);

        JButton voltarCurso = new JButton("Voltar");
        voltarCurso.setBounds(276, 20, 50, 30);
        voltarCurso.setBackground(Color.decode("#2e2e2e"));
        voltarCurso.setForeground(Color.decode("#D9D9D9"));
        voltarCurso.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        voltarCurso.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        voltarCurso.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(voltarCurso, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(voltarCurso);

        JTextField campoCurso = new JTextField("");
        campoCurso.setBounds(54, 132, 120, 21);
        campoCurso.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        campoCurso.setBackground(Color.decode("#B2B2B2"));
        campoCurso.setForeground(Color.decode("#656565"));
        campoCurso.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
        OnFocusEventHelper.setOnFocusText(campoCurso, "Insira o nome...", Color.decode("#353535"),   Color.decode("#656565"));
        panel.add(campoCurso);

        JTextField campoCarga = new JTextField("");
        campoCarga.setBounds(55, 162, 120, 21);
        campoCarga.setFont(CustomFontLoader.loadFont("./fonts/Lato.ttf", 14));
        campoCarga.setBackground(Color.decode("#B2B2B2"));
        campoCarga.setForeground(Color.decode("#656565"));
        campoCarga.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
        OnFocusEventHelper.setOnFocusText(campoCarga, "Insira a carga horária...", Color.decode("#353535"),   Color.decode("#656565"));
        panel.add(campoCarga);

        frame.add(panel);
        frame.setVisible(true);
    }
}
