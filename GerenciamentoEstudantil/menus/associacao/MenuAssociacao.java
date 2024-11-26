package menus.associacao;

import dao.AssociarEstudanteDAO;
import dao.AssociarProfessorDAO;

import javax.swing.*;
import java.awt.*;

public class MenuAssociacao {
    private JFrame frame;
    private AssociarProfessorDAO professorDAO;
    private AssociarEstudanteDAO estudanteDAO;

    public MenuAssociacao(JFrame parent) {
        professorDAO = new AssociarProfessorDAO();
        estudanteDAO = new AssociarEstudanteDAO();

        frame = new JFrame("Associar Alunos e Professores");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(parent);

        construirMenu();
    }

    private void construirMenu() {
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(102, 113, 117));

        JLabel labelTitulo = new JLabel("Menu de Associação");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setBounds(25, 10, 300, 30);

        JButton btnAssociarProfessor = new JButton("Associar Professor");
        JButton btnAssociarEstudante = new JButton("Associar Estudante");
        JButton btnRemoverProfessor = new JButton("Remover Professor de Curso");
        JButton btnRemoverEstudante = new JButton("Remover Estudante de Curso");
        JButton btnVoltar = new JButton("Voltar");

        // Configuração de botões
        btnAssociarProfessor.setBounds(25, 50, 250, 30);
        btnAssociarEstudante.setBounds(25, 100, 250, 30);
        btnRemoverProfessor.setBounds(25, 150, 250, 30);
        btnRemoverEstudante.setBounds(25, 200, 250, 30);
        btnVoltar.setBounds(300, 225, 75, 30);

        // Adicionar ações aos botões
        btnAssociarProfessor.addActionListener(e -> associarProfessor());
        btnAssociarEstudante.addActionListener(e -> associarEstudante());
        btnRemoverProfessor.addActionListener(e -> removerProfessor());
        btnRemoverEstudante.addActionListener(e -> removerEstudante());
        btnVoltar.addActionListener(e -> frame.dispose());

        // Adicionar componentes ao painel
        painel.add(labelTitulo);
        painel.add(btnAssociarProfessor);
        painel.add(btnAssociarEstudante);
        painel.add(btnRemoverProfessor);
        painel.add(btnRemoverEstudante);
        painel.add(btnVoltar);

        frame.add(painel);
        frame.setVisible(true);
    }

    private void associarProfessor() {
        int professorId = obterId("Digite o ID do professor:");
        int cursoId = obterId("Digite o ID do curso:");
        if (professorDAO.associarProfessor(professorId, cursoId)) {
            JOptionPane.showMessageDialog(frame, "Professor associado ao curso com sucesso!");
        } else {
            JOptionPane.showMessageDialog(frame, "Falha ao associar o professor ao curso.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void associarEstudante() {
        int estudanteId = obterId("Digite o ID do estudante:");
        int cursoId = obterId("Digite o ID do curso:");
        if (estudanteDAO.associarEstudante(estudanteId, cursoId)) {
            JOptionPane.showMessageDialog(frame, "Estudante associado ao curso com sucesso!");
        } else {
            JOptionPane.showMessageDialog(frame, "Falha ao associar o estudante ao curso.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerProfessor() {
        int professorId = obterId("Digite o ID do professor:");
        int cursoId = obterId("Digite o ID do curso:");

        if (professorId != -1 && cursoId != -1) {
            if (professorDAO.removerProfessor(professorId, cursoId)) {
                JOptionPane.showMessageDialog(frame, "Professor removido do curso com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Falha ao remover o professor do curso. Verifique se o professor está associado ao curso.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerEstudante() {
        int estudanteId = obterId("Digite o ID do estudante:");
        int cursoId = obterId("Digite o ID do curso:");
        if (estudanteDAO.removerEstudante(estudanteId, cursoId)) {
            JOptionPane.showMessageDialog(frame, "Estudante removido do curso com sucesso!");
        } else {
            JOptionPane.showMessageDialog(frame, "Falha ao remover o estudante do curso.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obterId(String mensagem) {
        String input = JOptionPane.showInputDialog(frame, mensagem);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "ID inválido. Por favor, insira um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public void exibir() {
        frame.setVisible(true);
    }
}