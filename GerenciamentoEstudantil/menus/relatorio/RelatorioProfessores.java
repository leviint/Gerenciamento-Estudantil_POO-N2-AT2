package menus.relatorio;

import javax.swing.*;
import java.awt.*;
import dao.AssociarProfessorDAO;
import java.util.List;

public class RelatorioProfessores {

    private JFrame frame;
    private AssociarProfessorDAO professorDAO;

    public RelatorioProfessores() {
        professorDAO = new AssociarProfessorDAO();
        frame = new JFrame("Relatório de Professores");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void exibir() {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Relatório de Professores", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        painel.add(titulo, BorderLayout.NORTH);

        // Lista de professores e cursos
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Buscar professores e cursos
        List<String> relatorio = professorDAO.gerarRelatorioProfessores();
        for (String linha : relatorio) {
            textArea.append(linha + "\n");
        }

        JScrollPane scroll = new JScrollPane(textArea);
        painel.add(scroll, BorderLayout.CENTER);

        // Botão Fechar
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> frame.dispose());
        painel.add(btnFechar, BorderLayout.SOUTH);

        // Exibir janela
        frame.add(painel);
        frame.setVisible(true);
    }
}
