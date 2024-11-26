package menus.curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.CursoDAO;
import menus.MenuBase;
import models.Curso;
import java.util.List;

public class CursoConsulta extends MenuBase {
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;
    private CursoDAO cursoDAO;

    public CursoConsulta(JFrame menuPrincipal) {
        super("Consultar Cursos", menuPrincipal);
        cursoDAO = new CursoDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Consulta de Cursos");
        JLabel subtitulo = new JLabel("Lista de Cursos Cadastrados");
        configurarEstilo(label, subtitulo);

        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Carga HorÃ¡ria"}, 0);
        tabelaResultados = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaResultados);
        scrollPane.setBounds(25, 50, 400, 300);
        painel.add(scrollPane);

        adicionarBotao(new JButton("Voltar"), 350, 360, 100, 30, this::fechar);

        carregarCursos();
    }

    private void carregarCursos() {
        modeloTabela.setRowCount(0);
        List<Curso> cursos = cursoDAO.getCursos();
        cursos.forEach(curso -> modeloTabela.addRow(new Object[]{curso.getId(), curso.getNomeCurso(), curso.getCargaHoraria()}));
    }
}