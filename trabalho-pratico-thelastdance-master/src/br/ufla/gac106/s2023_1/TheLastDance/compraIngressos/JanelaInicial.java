package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Classe que exibe a janela inicial de compra de ingressos ao usuário
 */
public class JanelaInicial extends JanelaBase{

    
    /*
     * Construtor da classe JanelaEventos
     */
    public JanelaInicial() {
        super("TheLastDance - Ingressos", "Seja bem vindo(a) ao TheLastDance, um sistema de compra de ingressos de shows musicais",
            1024, 768, false, null, true, true);
    }

    /*
     * Exibe a janela inicial
     */
    public void iniciar() {
        setVisible(true);
    }

    /*
     * Cria o painel central
     */
    @Override
    public JPanel criarPainelCentral() {
        JPanel painelCentral = new JPanel();

        return painelCentral;
    }

    /*
     * Trata o evento de clique do botão Avançar
     */
    @Override
    public boolean aoAvancar() {
        JanelaEventos jEventos = new JanelaEventos(this);
        jEventos.exibir();
        return super.aoAvancar();
    }

    /*
     * Trata o evento de clique do botão Avançar
     */
    @Override
    public boolean aoFinalizar() {
        JOptionPane.showConfirmDialog(this, "Programa Encerrado", "Aviso", JOptionPane.CLOSED_OPTION);
        return super.aoFinalizar();
    }
}
