package menus.professor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dao.ProfessorDAO;
import menus.MenuBase;
import models.Professor;

public class ProfessorConsulta extends MenuBase {
    private JTextField campoNome;
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;
    private ProfessorDAO professorDAO;

    public ProfessorConsulta(JFrame menuProfessor) {
        super("Consultar Professor", menuProfessor);
        professorDAO = new ProfessorDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Docente");
        JLabel subtitulo = new JLabel("Consultar Professor");
        configurarEstilo(label, subtitulo);

        // Campo de entrada para nome
        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        campoNome = new JTextField();
        adicionarCampo(campoNome, 25, 80, 200, 30, "");

        // Botões
        adicionarBotao(new JButton("Consultar"), 25, 120, 200, 30, this::pesquisarProfessores);
        adicionarBotao(new JButton("Voltar"), 250, 120, 75, 30, this::fechar);

        // Tabela para exibir resultados
        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Idade", "Especialidade"}, 0);
        tabelaResultados = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaResultados);
        scrollPane.setBounds(25, 160, 320, 150);
        painel.add(scrollPane);
    }

    private void pesquisarProfessores() {
        String nomePesquisa = campoNome.getText().trim();

        if (nomePesquisa.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um nome para pesquisar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Limpar tabela
        modeloTabela.setRowCount(0);

        // Buscar professores no banco
        List<Professor> professores = professorDAO.getProfessor();

        // Filtrar pelo nome
        professores.stream()
                .filter(p -> p.getNome().toLowerCase().contains(nomePesquisa.toLowerCase()))
                .forEach(p -> modeloTabela.addRow(new Object[]{p.getNome(), p.getIdade(), p.getEspecialidade()}));

        if (modeloTabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Nenhum professor encontrado.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}