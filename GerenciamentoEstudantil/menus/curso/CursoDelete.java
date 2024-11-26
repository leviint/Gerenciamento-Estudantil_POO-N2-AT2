package menus.curso;

import javax.swing.*;
import dao.CursoDAO;
import menus.MenuBase;

public class CursoDelete extends MenuBase {
    private JTextField campoId;
    private CursoDAO cursoDAO;

    public CursoDelete(JFrame menuPrincipal) {
        super("Excluir Curso", menuPrincipal);
        cursoDAO = new CursoDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Excluir Curso");
        JLabel subtitulo = new JLabel("Insira o ID do curso para excluir");
        configurarEstilo(label, subtitulo);

        adicionarTexto("ID do Curso:", 25, 50, 200, 30, 14);
        campoId = new JTextField();
        adicionarCampo(campoId, 25, 80, 200, 30, "");

        adicionarBotao(new JButton("Excluir"), 25, 120, 200, 30, this::excluirCurso);
        adicionarBotao(new JButton("Voltar"), 250, 120, 100, 30, this::fechar);
    }

    private void excluirCurso() {
        String idTexto = campoId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira o ID do curso para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            cursoDAO.deleteById(id);
            JOptionPane.showMessageDialog(frame, "Curso excluído com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "ID inválido! Deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao excluir curso: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}