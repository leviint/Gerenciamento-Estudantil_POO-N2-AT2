package menus.curso;

import javax.swing.*;
import dao.CursoDAO;
import menus.MenuBase;
import models.Curso;

public class CursoUpdate extends MenuBase {
    private JTextField campoId;
    private JTextField campoNomeCurso;
    private JTextField campoCargaHoraria;
    private CursoDAO cursoDAO;

    public CursoUpdate(JFrame menuPrincipal) {
        super("Atualizar Curso", menuPrincipal);
        cursoDAO = new CursoDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Atualizar Curso");
        JLabel subtitulo = new JLabel("Insira as informações do curso");
        configurarEstilo(label, subtitulo);

        adicionarTexto("ID do Curso:", 25, 50, 200, 30, 14);
        campoId = new JTextField();
        adicionarCampo(campoId, 25, 80, 200, 30, "");

        adicionarTexto("Novo Nome:", 25, 120, 200, 30, 14);
        campoNomeCurso = new JTextField();
        adicionarCampo(campoNomeCurso, 25, 150, 200, 30, "");

        adicionarTexto("Nova Carga Horária:", 25, 190, 200, 30, 14);
        campoCargaHoraria = new JTextField();
        adicionarCampo(campoCargaHoraria, 25, 220, 200, 30, "");

        adicionarBotao(new JButton("Atualizar"), 25, 270, 200, 30, this::atualizarCurso);
        adicionarBotao(new JButton("Voltar"), 250, 270, 100, 30, this::fechar);
    }

    private void atualizarCurso() {
        String idTexto = campoId.getText().trim();
        String nomeCurso = campoNomeCurso.getText().trim();
        String cargaHorariaTexto = campoCargaHoraria.getText().trim();

        if (idTexto.isEmpty() || nomeCurso.isEmpty() || cargaHorariaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            int cargaHoraria = Integer.parseInt(cargaHorariaTexto);

            Curso curso = new Curso();
            curso.setId(id);
            curso.setNomeCurso(nomeCurso);
            curso.setCargaHoraria(cargaHoraria);

            cursoDAO.update(curso);

            JOptionPane.showMessageDialog(frame, "Curso atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "ID e carga horária devem ser números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao atualizar curso: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}