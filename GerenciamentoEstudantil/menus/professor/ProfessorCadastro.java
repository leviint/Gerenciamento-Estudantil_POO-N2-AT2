package menus.professor;

import javax.swing.*;

import dao.EstudanteDAO;
import dao.ProfessorDAO;
import menus.MenuBase;
import models.Estudante;
import models.Professor;

public class ProfessorCadastro extends MenuBase{
    private JTextField campoNome;
    private JTextField campoIdade;
    private JTextField campoEspecialidade;

    public ProfessorCadastro(JFrame MenuProfessor){
        super("Cadastrar Professor", MenuProfessor);
        construirMenu();
    }

    ProfessorDAO instancia = new ProfessorDAO();

    @Override
    protected void construirMenu(){
        JLabel label = new JLabel("Gerenciamento Estudantil");
        JLabel subtitulo = new JLabel("Cadastrar Professor");
        configurarEstilo(label, subtitulo);

        adicionarTexto("Insira o nome:", 25, 50, 200, 30, 14);
        campoNome = new JTextField();
        adicionarCampo(campoNome, 25, 80, 200, 30, null);

        adicionarTexto("Insira a idade:", 25, 110, 200, 30, 14);
        campoIdade = new JTextField();
        adicionarCampo(campoIdade, 25, 140, 200, 30, null);

        adicionarTexto("Insira a especialidade:", 25, 170, 200, 30, 14);
        campoEspecialidade = new JTextField();
        adicionarCampo(campoEspecialidade, 25, 200, 200, 30, null);

        adicionarBotao(new JButton("Cadastrar"), 250, 190, 95, 30, this::salvarProfessor);
        adicionarBotao(new JButton("Voltar"), 250, 225, 75, 30, this::fechar);
    }

    private void salvarProfessor() {
        try {
            // Recupera os valores dos campos
            String nome = campoNome.getText().trim();
            String especialidade = campoEspecialidade.getText().trim();
            String idadeTexto = campoIdade.getText().trim();

            // Validação básica
            if (nome.isEmpty() || especialidade.isEmpty() || idadeTexto.isEmpty()) {
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
            Professor professor = new Professor();
            professor.setNome(nome);
            professor.setIdade(idade);
            professor.setEspecialidade(especialidade);

            // Salva o estudante no banco
            instancia.save(professor);

            JOptionPane.showMessageDialog(frame, "Professor cadastrado com sucesso!");

            // Limpa os campos após salvar
            campoNome.setText("");
            campoEspecialidade.setText("");
            campoIdade.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar professor: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
