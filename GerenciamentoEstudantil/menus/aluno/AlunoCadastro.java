package menus.aluno;

import javax.swing.*;
import menus.MenuBase;
import dao.EstudanteDAO;
import models.Estudante;

public class AlunoCadastro extends MenuBase {
    private JTextField campoNome;
    private JTextField campoIdade;
    private JTextField campoMatricula;

    public AlunoCadastro(JFrame menuAluno) {
        super("Cadastrar Aluno", menuAluno);
        construirMenu();
    }

    EstudanteDAO instancia = new EstudanteDAO();

    @Override
    protected void construirMenu() {
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Cadastrar Aluno");
        configurarEstilo(label, subtitulo);

        // Campos de entrada
        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        campoNome = new JTextField();
        adicionarCampo(campoNome, 25, 80, 200, 30, "");

        adicionarTexto("Insira a idade:", 25, 110, 200, 30, 14);
        campoIdade = new JTextField();
        adicionarCampo(campoIdade, 25, 140, 200, 30, "");

        adicionarTexto("Insira a matrícula:", 25, 170, 200, 30, 14);
        campoMatricula = new JTextField();
        adicionarCampo(campoMatricula, 25, 200, 200, 30, "");

        // Botões
        adicionarBotao(new JButton("Cadastrar"), 250, 190, 95, 30, this::salvarEstudante);
        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }

    private void salvarEstudante() {
        try {
            // Recupera os valores dos campos
            String nome = campoNome.getText().trim();
            String idadeTexto = campoIdade.getText().trim();
            String matricula = campoMatricula.getText().trim();

            // Validação básica
            if (nome.isEmpty() || idadeTexto.isEmpty() || matricula.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idade;
            try {
                idade = Integer.parseInt(idadeTexto); // Converte idade para número
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Idade deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cria o objeto Estudante
            Estudante estudante = new Estudante();
            estudante.setNome(nome);
            estudante.setIdade(idade);
            estudante.setMatricula(matricula);

            // Salva o estudante no banco
            instancia.save(estudante);

            JOptionPane.showMessageDialog(frame, "Estudante cadastrado com sucesso!");

            // Limpa os campos após salvar
            campoNome.setText("");
            campoIdade.setText("");
            campoMatricula.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar estudante: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}