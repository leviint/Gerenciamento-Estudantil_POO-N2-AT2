package menus;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class MenuBase {
    protected JFrame frame;
    protected JPanel painel;

    public MenuBase(String titulo, JFrame janelaPrincipal) {
        // Configuração da janela principal
        frame = new JFrame(titulo);
        frame.setSize(400, 300);
        Point localizacao = janelaPrincipal.getLocation();
        frame.setLocation(localizacao.x, localizacao.y);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Configuração do painel com layout mais moderno
        painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(45, 52, 54));
        frame.add(painel);

        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    protected void configurarEstilo(JLabel titulo, JLabel subtitulo) {
        Font fonteTitulo = new Font("Arial", Font.BOLD, 24);
        Font fonteSubtitulo = new Font("Arial", Font.PLAIN, 14);
        titulo.setFont(fonteTitulo);
        titulo.setForeground(new Color(255, 255, 255));
        subtitulo.setFont(fonteSubtitulo);
        subtitulo.setForeground(new Color(200, 200, 200));

        painel.add(titulo);
        painel.add(subtitulo);
        titulo.setBounds(20, 10, 360, 30);
        subtitulo.setBounds(20, 45, 360, 20);
    }

    // Adicionar botões estilizados
    protected void adicionarBotao(JButton botao, int x, int y, int largura, int altura, Runnable acao) {
        botao.setBounds(x, y, largura, altura);
        botao.setBackground(new Color(41, 128, 185));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false); // Remove o contorno de foco
        botao.setBorder(BorderFactory.createEmptyBorder()); // Remove bordas padrão

        // Adiciona efeito de hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(31, 97, 141));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(41, 128, 185));
            }
        });

        botao.addActionListener(_ -> acao.run());
        painel.add(botao);
    }

    // Método para adicionar campos de texto com estilo
    protected void adicionarCampo(JTextField campo, int x, int y, int largura, int altura, String texto) {
        campo.setBounds(x, y, largura, altura);
        campo.setText(texto);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(Color.BLACK);
        campo.setBackground(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(149, 165, 166), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        painel.add(campo);
    }

    // Adicionar texto com mais opções de estilo
    protected void adicionarTexto(String texto, int x, int y, int largura, int altura, int tamanhoFonte) {
        JLabel label = new JLabel(texto);
        Font fonte = new Font("Arial", Font.BOLD, tamanhoFonte);
        label.setFont(fonte);
        label.setForeground(new Color(236, 240, 241));
        label.setBounds(x, y, largura, altura);
        painel.add(label);
    }

    public void exibir() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    protected void fechar() {
        frame.dispose();
    }

    protected abstract void construirMenu();
}
