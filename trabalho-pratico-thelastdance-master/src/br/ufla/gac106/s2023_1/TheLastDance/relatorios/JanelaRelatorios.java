package br.ufla.gac106.s2023_1.TheLastDance.relatorios;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufla.gac106.s2023_1.TheLastDance.compraIngressos.JanelaBase;

/*
 * Classe que exibe uma interface gráfica para que o usuário seleciona o tipo de dados do qual será gerado um relatório
 */
public class JanelaRelatorios extends JanelaBase{
    private IdentificadorDados idDados;                                 // Identificador do tipo de dados do qual será gerado o relatório   
    private JComboBox<IdentificadorDados> caixaIdDados;                 // Caixa de seleção de identificadores
    private JComboBox<String> caixaTipoDados;                           // Caixa de seleção de tipo de dados
    private boolean valorArrecadado;                                    // Tipo de dado selecionado pelo usuário (true se for valor arrecadado e false se for quantidade de ingressos)
    private String valor = "Valor arrecadado";                          // Texto que aparece como opção no JComboBox  
    private String qtd = "Quantidade de Ingressos";                          // Texto que aparece como opção no JComboBox
    private JPanel painelCentral;                                       // Painel central da janela
    
    /*
     * Construtor da classe JanelaRelatorios
     */
    JanelaRelatorios() {
        super("Relatórios", "Relatórios de compras de ingressos no sistema", 1024, 768, false, null, true, false);
        atualizarPainelCentral();
    }

     /*
     * Exibe a janela na tela
     */
    public void exibir() {
        setVisible(true);
    }

    /*
     * Cria o painel central
     */
    @Override
    public JPanel criarPainelCentral() {
        painelCentral = new JPanel();

        return painelCentral;
    }

    /*
     * Atualiza o painel central
     */
    private void atualizarPainelCentral() {
        JLabel pedirId = new JLabel("Selecione para qual grupo deseja visualizar os dados");
        JLabel pedirTipoDados = new JLabel("Selecione o tipo de dados desejado");
        criarComponentes();

        painelCentral.setLayout(new GridLayout(20, 2));
        painelCentral.add(pedirId);
        painelCentral.add(caixaIdDados);
        painelCentral.add(pedirTipoDados);
        painelCentral.add(caixaTipoDados);

        atualizarCaixaIdDados();
        atualizarCaixaTipoDados();
    }

    /*
     * Cria os componentes do painel central
     */
    public void criarComponentes() {
        caixaIdDados = new JComboBox<IdentificadorDados>();
        caixaTipoDados = new JComboBox<String>();
         
        // adiciona o método que tratará o evento de clique na caixa de seleção de identificador
        caixaIdDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tratarSelecaoId();
            }
        });

        // adiciona o método que tratará o evento de clique na caixa de seleção de tipo de dados
        caixaIdDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tratarSelecaoTipoDados();
            }
        });
    }

    /*
     * 
     * Trata a opção selecionada na caixa de seleção de identificadores
     */
    public void tratarSelecaoId() {
        idDados = (IdentificadorDados)caixaIdDados.getSelectedItem();
    }

    /*
     * 
     * Trata a opção selecionada na caixa de seleção de tipo de dados
     */
    public void tratarSelecaoTipoDados() {
        if(String.valueOf(caixaTipoDados.getSelectedItem()).equals(valor)) {
            valorArrecadado = true;
        } else {
            valorArrecadado = false;
        }
    }

    /*
     * Atualiza a caixa de seleção de identificadores
     */
    private void atualizarCaixaIdDados() {
        caixaIdDados.removeAllItems();

        for(IdentificadorDados id : IdentificadorDados.values()) {
            caixaIdDados.addItem(id);
        }
    }

    /*
     * Atualiza a caixa de seleção de tipo de dados
     */
    private void atualizarCaixaTipoDados() {
        caixaTipoDados.removeAllItems();

        caixaTipoDados.addItem(valor);
        caixaTipoDados.addItem(qtd);
    }

    /*
     * Trata o evento de clique do botão Avançar
     */
    @Override
    public boolean aoAvancar() {
        GerenciadorIngressosVendidos gIngVend = new GerenciadorIngressosVendidos();
        GraficoIngressos gi = new GraficoIngressos();
   
        gi.exibir(idDados.getDescricaoId(), gIngVend.getListaIngressosPorId(idDados) , valorArrecadado);
        return super.aoAvancar();
    }
}
