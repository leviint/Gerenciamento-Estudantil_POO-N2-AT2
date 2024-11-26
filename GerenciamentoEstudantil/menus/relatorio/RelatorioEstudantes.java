package menus.relatorio;

import javax.swing.*;
import java.awt.*;
import dao.AssociarEstudanteDAO;
import java.util.List;

public class RelatorioEstudantes {

    private JFrame frame;
    private AssociarEstudanteDAO estudanteDAO;

    public RelatorioEstudantes() {
        estudanteDAO = new AssociarEstudanteDAO();
        frame = new JFrame("Relatório de Estudantes");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void exibir() {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Relatório de Estudantes", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        painel.add(titulo, BorderLayout.NORTH);

        // Lista de estudantes e cursos
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Buscar estudantes e cursos
        List<String> relatorio = estudanteDAO.gerarRelatorioEstudantes();
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
