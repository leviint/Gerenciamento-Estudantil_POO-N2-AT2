package menus.aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import dao.EstudanteDAO;
import menus.MenuBase;
import models.Estudante;

public class AlunoUpdate extends MenuBase {
    private JTextField campoNome;
    private JTextField campoNovaIdade;
    private JTextField campoNovaMatricula;
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;
    private EstudanteDAO estudanteDAO;

    public AlunoUpdate(JFrame menuAluno) {
        super("Atualizar Aluno", menuAluno);
        estudanteDAO = new EstudanteDAO();
        construirMenu();
    }

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Atualizar Aluno");
        configurarEstilo(label, subtitulo);

        // Campo de entrada para nome
        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        campoNome = new JTextField();
        adicionarCampo(campoNome, 25, 80, 200, 30, "");

        // Campo para nova idade
        adicionarTexto("Nova Idade:", 250, 50, 200, 30, 14);
        campoNovaIdade = new JTextField();
        adicionarCampo(campoNovaIdade, 250, 80, 100, 30, "");

        // Campo para nova matrícula
        adicionarTexto("Nova Matrícula:", 250, 110, 200, 30, 14);
        campoNovaMatricula = new JTextField();
        adicionarCampo(campoNovaMatricula, 250, 140, 100, 30, "");

        // Botões
        adicionarBotao(new JButton("Consultar"), 25, 120, 200, 30, this::pesquisarEstudantes);
        adicionarBotao(new JButton("Atualizar"), 25, 160, 200, 30, this::atualizarEstudante);
        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);

        // Tabela para exibir resultados
        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Idade", "Matrícula"}, 0);
        tabelaResultados = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaResultados);
        scrollPane.setBounds(25, 200, 320, 150);
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

    private void atualizarEstudante() {
        int selectedRow = tabelaResultados.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Por favor, selecione um estudante na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Recuperar dados da linha selecionada
        String nomeAtual = (String) modeloTabela.getValueAt(selectedRow, 0); // Nome atual do estudante (base para busca)
        String novaIdadeTexto = campoNovaIdade.getText().trim();
        String novaMatricula = campoNovaMatricula.getText().trim();

        if (novaIdadeTexto.isEmpty() || novaMatricula.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos para atualização.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int novaIdade = Integer.parseInt(novaIdadeTexto);

            // Criação do objeto Estudante com os novos dados
            Estudante estudante = new Estudante();
            estudante.setNome(nomeAtual); // Nome original usado como chave de identificação
            estudante.setIdade(novaIdade);
            estudante.setMatricula(novaMatricula);

            // Atualizar no banco de dados
            estudanteDAO.update(estudante); // Chamando o método corrigido no DAO
            JOptionPane.showMessageDialog(frame, "Estudante atualizado com sucesso!");
            pesquisarEstudantes(); // Atualiza a tabela para refletir as alterações
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "A idade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao atualizar estudante: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}