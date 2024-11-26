package menus.curso;

import javax.swing.*;
import dao.CursoDAO;
import menus.MenuBase;
import models.Curso;

public class CursoCadastro extends MenuBase {
    private JTextField campoNomeCurso;
    private JTextField campoCargaHoraria;
    private CursoDAO cursoDAO;

    public CursoCadastro(JFrame menuPrincipal) {
        super("Cadastrar Curso", menuPrincipal);
        cursoDAO = new CursoDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Cadastro de Curso");
        JLabel subtitulo = new JLabel("Insira as informações do curso");
        configurarEstilo(label, subtitulo);

        adicionarTexto("Nome do Curso:", 25, 50, 200, 30, 14);
        campoNomeCurso = new JTextField();
        adicionarCampo(campoNomeCurso, 25, 80, 200, 30, "");

        adicionarTexto("Carga Horária:", 25, 120, 200, 30, 14);
        campoCargaHoraria = new JTextField();
        adicionarCampo(campoCargaHoraria, 25, 150, 200, 30, "");

        adicionarBotao(new JButton("Salvar"), 25, 200, 200, 30, this::cadastrarCurso);
        adicionarBotao(new JButton("Voltar"), 250, 200, 100, 30, this::fechar);
    }

    private void cadastrarCurso() {
        String nomeCurso = campoNomeCurso.getText().trim();
        String cargaHorariaTexto = campoCargaHoraria.getText().trim();

        if (nomeCurso.isEmpty() || cargaHorariaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int cargaHoraria = Integer.parseInt(cargaHorariaTexto);

            Curso curso = new Curso();
            curso.setNomeCurso(nomeCurso);
            curso.setCargaHoraria(cargaHoraria);

            cursoDAO.save(curso);

            JOptionPane.showMessageDialog(frame, "Curso cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "A carga horária deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao cadastrar curso: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
