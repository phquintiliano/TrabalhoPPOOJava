package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

import br.ufla.gac106.s2023_1.TheLastDance.dados.DadosTurne;
import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Turne;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.Map;

public class JanelaConfirmarCompra extends JanelaBase{
        private Map<String, Turne> turnes;                  // Lista de turnês
        private String nomeUsuario;                         // Nome do usuário
        private ControladoraIngressos controlIngressos;     // Objeto que controla os ingressos do show selecionado
        private JPanel painelCentral;                       // Painel central da janela

    /*
     * Construtor da calsse JanelaConfirmarCompra
     */
    public JanelaConfirmarCompra(JanelaBase janelaAnterior, Map<String, Turne> turnes, String nomeUsuario, ControladoraIngressos controlIngressos) {
        super("Confirmar compra", "Revise seu pedido e confirme sua compra selecionando a opção Finalizar. \nCaso deseje alterar alguma informação, selecione a opção Voltar", 1024, 768, true, janelaAnterior, false, true);
        this.turnes = turnes;
        this.controlIngressos = controlIngressos;
        this.nomeUsuario = nomeUsuario;
        atualizarPainelCentral();
    }

    /*
     * Altera a visibilidade da janela pra true
     */
    public void exibir() {
        setVisible(true);
    }

    /*
     * Cria o painel central
     */
    public JPanel criarPainelCentral() {
        painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(6, 2));

        return painelCentral;
    }

    /*
     * Atualiza o painel central
     */
    public void atualizarPainelCentral() {
        JLabel labelNomeUsuario = new JLabel("Nome: " + nomeUsuario);
        JLabel labelNomeShow = new JLabel("Show selecionado: " + controlIngressos.getNomeShow());
        JLabel labelIngressoComum = new JLabel("Total de ingressos comuns: " + controlIngressos.getQtdIngressoComum());
        JLabel labelMeiaEntrada = new JLabel("Total de ingressos meia entrada: " + controlIngressos.getQtdMeiaEntrada());
        JLabel labelIngressoDesconto = new JLabel("Total de ingressos com desconto: " + controlIngressos.getQtdIngressoDesconto());
        JLabel labelTotalCompra = new JLabel("Valor total da compra: " + ControladoraIngressos.formatarDouble(controlIngressos.getValorTotal()));

        painelCentral.add(labelNomeUsuario);
        painelCentral.add(new JLabel());
        painelCentral.add(labelNomeShow);
        painelCentral.add(new JLabel());
        painelCentral.add(labelIngressoComum);
        painelCentral.add(labelMeiaEntrada);
        painelCentral.add(labelIngressoDesconto);
        painelCentral.add(labelTotalCompra);
    }

    /*
     * Gera um arquivo PDF com os dados dos ingressos comprados
     */
    private boolean gerarPDF() {
        GeradorDePDF geradorDePDF = new GeradorDePDF("ingressosTheLastDance.pdf", controlIngressos);
        return geradorDePDF.gerarPDF();
    }

    /*
     * Trata o evento de clique do botão Voltar
     */
    @Override 
    public boolean aoVoltar() {
        // Limpa os dados de ingressos passados pelo usuário na janela anterior antes de voltar
        controlIngressos.limparDadosIngressos();
        return true;
    }

    /* 
     * Trata o evento de clique do botão Finalizar
     */
    @Override
    public boolean aoFinalizar() {
        // Pergunta ao usuário se ele deseja finalizar a compra
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja finalizar sua compra?", "Confirmar", JOptionPane.YES_NO_OPTION);

        // Continua na tela caso a opção escolhida seja "não"
        if(resposta != JOptionPane.YES_OPTION) {
            return false;
        }

        // Confirma se foi possível comprar os ingressos
        if(!controlIngressos.comprarIngressos()) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar a compra", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Tenta salvar a lista de turnês no arquivo
        try{
            DadosTurne.salvarTurnes(turnes);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Erro no sistema. Não foi possível finalizar a compra", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        JOptionPane.showMessageDialog(this, "Compra realizada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Gera o arquivo pdf e informa ao usuário
        if(gerarPDF()) {
            JOptionPane.showMessageDialog(this, "Os ingressos foram salvos em um arquivo PDF", "Erro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível gerar o arquivo com os ingressos", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Salva a lista de ingressos vendidos no arquivo
        try {
            controlIngressos.salvarIngressos();
        } catch (RuntimeException e) {
            // adicionar erro ao arquivo log
        }

        // Finaliza o programa
        return super.aoFinalizar();
    }
}

