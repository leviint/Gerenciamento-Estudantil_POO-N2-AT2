package menus;

import java.awt.*;
import javax.swing.*;

public abstract class MenuBase {
    protected JFrame frame;
    protected JPanel painel;

    public MenuBase(String titulo, JFrame janelaPrincipal) {
        frame = new JFrame(titulo);
        frame.setSize(400, 300);
        Point localizacao = janelaPrincipal.getLocation();
        frame.setLocation(localizacao.x, localizacao.y);
        frame.setResizable(false);

        painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(102, 113, 117));
        frame.add(painel);
    }

    protected void configurarEstilo(JLabel label, JLabel subtitulo) {
        Font fonte = new Font("Arial", Font.BOLD, 20);
        label.setFont(fonte);
        label.setForeground(Color.WHITE);
        subtitulo.setForeground(Color.WHITE);
        painel.add(label);
        painel.add(subtitulo);
        label.setBounds(25, 0, 300, 30);
        subtitulo.setBounds(25, 25, 175, 30);
    }

    protected void adicionarBotao(JButton botao, int x, int y, int largura, int altura, Runnable acao) {
        botao.setBounds(x, y, largura, altura);
        botao.addActionListener(_ -> acao.run());
        painel.add(botao);
    }

    protected void adicionarCampo(JTextField campo, int x, int y, int largura, int altura, String texto){
        campo.setBounds(x, y, largura, altura);
        campo.setText(texto);
        painel.add(campo);
    }

    protected void adicionarTexto(String texto, int x, int y, int largura, int altura, int tamanhoFonte){
        JLabel label = new JLabel(texto);
        Font fonte = new Font("Arial", Font.BOLD, tamanhoFonte);
        label.setFont(fonte);
        label.setForeground(Color.WHITE);
        painel.add(label);
    }

    public void exibir() {
        frame.setVisible(true);
    }

    protected void fechar() {
        frame.dispose();
    }

    protected abstract void construirMenu();
}
