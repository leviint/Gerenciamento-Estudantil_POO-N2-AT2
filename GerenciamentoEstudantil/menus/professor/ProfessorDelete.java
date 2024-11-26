package menus.professor;

import javax.swing.*;
import dao.ProfessorDAO;
import menus.MenuBase;

public class ProfessorDelete extends MenuBase {
    private JTextField campoNome;
    private ProfessorDAO professorDAO;

    public ProfessorDelete(JFrame menuProfessor) {
        super("Excluir Professor", menuProfessor);
        professorDAO = new ProfessorDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Acadêmico");
        JLabel subtitulo = new JLabel("Excluir Professor");
        configurarEstilo(label, subtitulo);

        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        campoNome = new JTextField();
        adicionarCampo(campoNome, 25, 80, 200, 30, "");

        adicionarBotao(new JButton("Excluir"), 25, 120, 200, 30, this::excluirProfessor);
        adicionarBotao(new JButton("Voltar"), 250, 120, 100, 30, this::fechar);
    }

    private void excluirProfessor() {
        String nome = campoNome.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira o nome do professor para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            professorDAO.deleteByName(nome);
            JOptionPane.showMessageDialog(frame, "Professor excluído com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao excluir professor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}