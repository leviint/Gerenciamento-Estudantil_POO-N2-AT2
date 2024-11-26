package menus.aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dao.EstudanteDAO;
import menus.MenuBase;
import models.Estudante;

public class AlunoConsulta extends MenuBase {
    private JTextField campoNome;
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;
    private EstudanteDAO estudanteDAO;

    public AlunoConsulta(JFrame menuAluno) {
        super("Consultar Aluno", menuAluno);
        estudanteDAO = new EstudanteDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Consultar Aluno");
        configurarEstilo(label, subtitulo);

        // Campo de entrada para nome
        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        campoNome = new JTextField();
        adicionarCampo(campoNome, 25, 80, 200, 30, "");

        // Botões
        adicionarBotao(new JButton("Consultar"), 25, 120, 200, 30, this::pesquisarEstudantes);
        adicionarBotao(new JButton("Voltar"), 250, 120, 75, 30, this::fechar);

        // Tabela para exibir resultados
        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Idade", "Matrícula"}, 0);
        tabelaResultados = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaResultados);
        scrollPane.setBounds(25, 160, 320, 150);
        painel.add(scrollPane);
    }

    private void pesquisarEstudantes() {
        String nomePesquisa = campoNome.getText().trim();

        if (nomePesquisa.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um nome para pesquisar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Limpar tabela
        modeloTabela.setRowCount(0);

        // Buscar estudantes no banco
        List<Estudante> estudantes = estudanteDAO.getEstudantes();

        // Filtrar pelo nome
        estudantes.stream()
                .filter(e -> e.getNome().toLowerCase().contains(nomePesquisa.toLowerCase()))
                .forEach(e -> modeloTabela.addRow(new Object[]{e.getNome(), e.getIdade(), e.getMatricula()}));

        if (modeloTabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Nenhum estudante encontrado.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}