package menus.professor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dao.ProfessorDAO;
import menus.MenuBase;
import models.Professor;

public class ProfessorUpdate extends MenuBase {
    private JTextField campoNome;
    private JTextField campoIdade;
    private JTextField campoEspecialidade;
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;
    private ProfessorDAO professorDAO;

    public ProfessorUpdate(JFrame menuProfessor) {
        super("Atualizar Professor", menuProfessor);
        professorDAO = new ProfessorDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento de Professores");
        JLabel subtitulo = new JLabel("Atualizar Informações de Professor");
        configurarEstilo(label, subtitulo);

        // Campos de entrada
        adicionarTexto("Nome do Professor:", 25, 50, 150, 30, 14);
        campoNome = new JTextField();
        adicionarCampo(campoNome, 180, 50, 180, 30, "");

        adicionarTexto("Nova Idade:", 25, 100, 150, 30, 14);
        campoIdade = new JTextField();
        adicionarCampo(campoIdade, 180, 100, 180, 30, "");

        adicionarTexto("Especialidade:", 25, 150, 150, 30, 14);
        campoEspecialidade = new JTextField();
        adicionarCampo(campoEspecialidade, 180, 150, 180, 30, "");

        // Botões
        adicionarBotao(new JButton("Consultar"), 25, 200, 150, 30, this::pesquisarProfessor);
        adicionarBotao(new JButton("Atualizar"), 180, 200, 150, 30, this::atualizarProfessor);
        adicionarBotao(new JButton("Voltar"), 340, 200, 100, 30, this::fechar);

        // Tabela de resultados
        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Idade", "Especialidade"}, 0);
        tabelaResultados = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaResultados);
        scrollPane.setBounds(25, 250, 420, 150);
        painel.add(scrollPane);

        carregarProfessores();
    }

    private void pesquisarProfessor() {
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
                .filter(e -> e.getNome().toLowerCase().contains(nomePesquisa.toLowerCase()))
                .forEach(e -> modeloTabela.addRow(new Object[]{e.getNome(), e.getIdade(), e.getEspecialidade()}));

        if (modeloTabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Nenhum professor encontrado.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void carregarProfessores() {
        // Limpar tabela
        modeloTabela.setRowCount(0);

        // Buscar professores no banco
        List<Professor> professores = professorDAO.getProfessor();

        // Adicionar à tabela
        professores.forEach(professor -> modeloTabela.addRow(new Object[]{
                professor.getNome(),
                professor.getIdade(),
                professor.getEspecialidade()
        }));
    }

    private void atualizarProfessor() {
        String nome = campoNome.getText().trim();
        String idadeTexto = campoIdade.getText().trim();
        String especialidade = campoEspecialidade.getText().trim();

        if (nome.isEmpty() || idadeTexto.isEmpty() || especialidade.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idade = Integer.parseInt(idadeTexto);

            // Criar objeto Professor para atualização
            Professor professor = new Professor();
            professor.setNome(nome);
            professor.setIdade(idade);
            professor.setEspecialidade(especialidade);

            // Atualizar no banco
            professorDAO.update(professor);

            JOptionPane.showMessageDialog(frame, "Professor atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Recarregar tabela
            carregarProfessores();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Idade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao atualizar professor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}