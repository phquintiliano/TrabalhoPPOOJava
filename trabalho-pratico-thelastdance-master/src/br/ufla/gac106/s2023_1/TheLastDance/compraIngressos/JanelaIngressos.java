package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Show;
import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Turne;

public class JanelaIngressos extends JanelaBase{
    private Map<String, Turne> turnes;              // Lista de turnês
    private Show show;                              // Show selecionado pelo usuário na janela anterior
    private JPanel painelCentral;                   // Painel central da janela          
    private ControladoraIngressos controlIngressos; // Classe que controla os ingressos 
    private JTextField nomeUsuario;                 // Caixa de texto que receberá o nome do usuário
    private JTextField ingressoComum;               // Caixa de texto que receberá a quantidade de ingressos comuns a serem comprados
    private JTextField meiaEntrada;                 // Caixa de texto que receberá a quantidade de ingressos meia entrada a serem comprados
    private JTextField ingressoDesconto;            // Caixa de texto que receberá a quantidade de ingressos com desconto a serem comprados
    private int qtdIngressosComuns;                 // Quantidade de ingressos comuns a serem comprados
    private int qtdMeiaEntrada;                     // Quantidade de ingressos meia entrada a serem comprados
    private int qtdIngressosDesconto;               // Quantidade de ingressos com desconto a serem comprados
    
    /*
     * Construtor da classe JanelaIngressos
     */
    public JanelaIngressos(JanelaBase janelaAnterior, Map<String, Turne> turnes, Show show) {
        super("Show: " + show.getNomeShow(), "Selecione o tipo de ingresso que deseja comprar", 1024, 768, true, janelaAnterior, true, false);
        this.turnes = turnes;
        this.show = show;
        this.controlIngressos = new ControladoraIngressos(show);
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
        painelCentral.setLayout(new GridLayout(10, 2));

        return painelCentral;
    }

    /*
     * Atualiza o painel central
     */
    private void atualizarPainelCentral() {
        JLabel labelNomeShow = new JLabel("Nome do show: " + show.getNomeShow());
        JLabel labelNomeCidade = new JLabel("Local: " + show.getDescricaoCidade());
        JLabel labelData = new JLabel("Data: " + show.getDia() + " - " + show.getHorario());
        JLabel labelQtdIngressos = new JLabel("Ingressos disponíveis: " + show.getQtdIngressos());

        JLabel labelNomeUsuario = new JLabel("Digite seu nome: ");
        JLabel labelIngressoComum = new JLabel("Informe a quantidade de ingressos comuns (R$" + ControladoraIngressos.formatarDouble(controlIngressos.getPrecoIngressoComum()) + " cada)");
        JLabel labelMeiaEntrada = new JLabel("Informe a quantidade de ingressos meia entrada (R$" + ControladoraIngressos.formatarDouble(controlIngressos.getPrecoIngressoMeiaEntrada()) + " cada)");
        JLabel labelIngressoDesconto = new JLabel("Informe a quantidade de ingressos com desconto (R$" + ControladoraIngressos.formatarDouble(controlIngressos.getPrecoIngressoDesconto()) + " cada)");

        nomeUsuario = new JTextField();
        ingressoComum = new JTextField("0");
        meiaEntrada = new JTextField("0");
        ingressoDesconto = new JTextField("0");

        painelCentral.add(labelNomeShow);
        painelCentral.add(labelNomeCidade);
        painelCentral.add(labelData);
        painelCentral.add(labelQtdIngressos);

        painelCentral.add(labelNomeUsuario);
        painelCentral.add(nomeUsuario);
        painelCentral.add(labelIngressoComum);
        painelCentral.add(ingressoComum);
        painelCentral.add(labelMeiaEntrada);
        painelCentral.add(meiaEntrada);
        painelCentral.add(labelIngressoDesconto);
        painelCentral.add(ingressoDesconto);
    }

    /*
     * // Converte para inteiro as quantidades de cada ingresso digitadas nas caixas de texto
     */
    private void converterIngressos() {
        try {
            qtdIngressosComuns = Integer.parseInt(ingressoComum.getText());
            qtdMeiaEntrada = Integer.parseInt(meiaEntrada.getText());
            qtdIngressosDesconto = Integer.parseInt(ingressoDesconto.getText());
        } catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }

    /*
     * Retorna true se houverem ingressos suficientes disponíveis
     */
    private boolean haIngressosSuficientes() {
        // Retorna true se o total de ingressos informados for menor ou igual ao total de ingressos disponíveis
        return controlIngressos.haIngressosSuficientes(qtdIngressosComuns, qtdIngressosDesconto, qtdMeiaEntrada);
    }

    /*
     * Adiciona os ingressos à lista de ingressos da controladora de ingressos
     */
    private void adicionarIngressos() {
        String nomeComprador = nomeUsuario.getText();
        controlIngressos.adicionarIngressosComuns(qtdIngressosComuns, nomeComprador);
        controlIngressos.adicionarIngressosDesconto(qtdIngressosDesconto, nomeComprador);
        controlIngressos.adicionarIngressosMeia(qtdMeiaEntrada, nomeComprador);
    }

    /*
     * Trata o evento de clique do botão Avançar
     */
    @Override
    public boolean aoAvancar() {
        // Verifica se o usuário digitou seu nome e, caso contrário, o informa
        if(nomeUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Nome não pode ficar em branco", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       
        try {
            converterIngressos();
        } catch (NumberFormatException e) {
            // Usuario digitou caracteres diferentes de números nas caixas de ingressos
            JOptionPane.showMessageDialog(this, "Digite apenas números nas caixas de ingressos", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Verifica se algum valor é menor do que 0 e informa ao usuário
        if(qtdIngressosComuns < 0 || qtdIngressosDesconto < 0 || qtdMeiaEntrada < 0) {
            JOptionPane.showMessageDialog(this, "Valores negativos na quantidade de ingressos serão considerados como 0", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        // Verifica se há ingressos suficientes
        if(!haIngressosSuficientes()) {
            JOptionPane.showMessageDialog(this, "Não há ingressos disponíveis suficientes. Total disponível = " + show.getQtdIngressos(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
            
        adicionarIngressos();

        // Verifica se se quantidade total de ingressos é maior que 0
        if(controlIngressos.getTotalIngressos() == 0) {
            JOptionPane.showMessageDialog(this, "A quantidade total de ingressos deve ser maior que 0" , "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Avança para a próxima janela
        JanelaConfirmarCompra jConfirmar = new JanelaConfirmarCompra(this, turnes, nomeUsuario.getText(), controlIngressos);
        jConfirmar.exibir();

        return super.aoAvancar();
    }
}
