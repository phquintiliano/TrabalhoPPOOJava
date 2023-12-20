package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.ITurne;
import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Show;
import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Turne;

import java.util.Map;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class JanelaAtividades extends JanelaBase{
    private Map<String, Turne> turnes;              // Lista de turnês
    private Map<String, Show> shows;                // Lista  de shows cadastrados para a turnê
    private Show showSelecionado;                   // Show selecionado pelo usuario na caixa de seleção
    private JComboBox<String> caixaSelecaoShows;    // Caixa de seleção de shows
    private ITurne turneAtual;                      // Turne selecionada pelo usuário na janela anterior
    private JPanel painelCentral;                   // Painel central da janela

    /*
     * Construtor da classe JanelaAtividades
     */
    public JanelaAtividades(JanelaBase janelaAnterior, Map<String, Turne> turnes, String nomeTurne) {
        super("Shows da turnê " + nomeTurne, "Selecione um show para visualizar suas informações", 1024, 768, true, janelaAnterior, true, true);
        this.turnes = turnes;
        turneAtual = turnes.get(nomeTurne);
        shows = turneAtual.getShows();
        atualizarPainelCentral();
    }

    /*
     * Cria o painel central
     */
    @Override
    public JPanel criarPainelCentral() {
        // Cria o painel central
        painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(10, 2));

        return painelCentral;
    }

    /*
     * Atualiza o painel central
     */
    private void atualizarPainelCentral() {
        JLabel labelNomeTurne = new JLabel(turneAtual.getNomeTurne());
        JLabel labelNomeBanda = new JLabel(turneAtual.getNomeBanda());
        JLabel labelEstiloMusical = new JLabel(turneAtual.getEstiloMusical());
        JLabel labelEntidadePromotora = new JLabel(turneAtual.getEntidadePromotora());

        painelCentral.add(labelNomeTurne);
        painelCentral.add(labelNomeBanda);
        painelCentral.add(labelEstiloMusical);
        painelCentral.add(labelEntidadePromotora);

        criarComponentes();
        painelCentral.add(caixaSelecaoShows);

        atualizarCaixaSelecaoShows();
    }

    /*
     * Cria os componentes do painel central
     */
    private void criarComponentes() {
        caixaSelecaoShows = new JComboBox<String>();

        // adiciona o método que tratará o evento de clique na caixa de seleção de autores
        caixaSelecaoShows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tratarSelecaoShow();
            }
        });
    }

    private void tratarSelecaoShow() {
        String nomeShowSelecionado = caixaSelecaoShows.getSelectedItem() + "";
        for(String nomeShow : shows.keySet()) {
            if(nomeShow.equals(nomeShowSelecionado)) {
                showSelecionado = shows.get(nomeShow);
            }       
        }
    }

    /*
     * Atualiza a caixa de seleção de shows com todas os shows existentes na lista
     */
    private void atualizarCaixaSelecaoShows() {
        caixaSelecaoShows.removeAllItems();

        // Verifica se existe algum show cadastrado para a turnê
        if(!shows.isEmpty()) {
            // Adiciona os shows da turnê na caixa de seleção
            for(String nomeShow : shows.keySet()) {
                caixaSelecaoShows.addItem(nomeShow);
            }
        }
    }

    /*
     * Altera a visibilidade da janela pra true
     */
    public void exibir() {
        setVisible(true);
    }

    /*
     * Trata o evento de clique do botão Avançar
     */
    @Override
    public boolean aoAvancar() {
        // Verifica se existem shows cadastrados para a turnê selecionada
        if(shows.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não existem shows cadastrados para essa turnê!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        // Verifica se há ingressos disponíveis para o show selecionado
        if(showSelecionado.getQtdIngressos() <= 0) {
            JOptionPane.showMessageDialog(this, "Não há ingressos disponíveis para o show selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Avança para a próxima janela
            JanelaIngressos jIngressos = new JanelaIngressos(this, turnes, showSelecionado);
            jIngressos.exibir();
            return super.aoAvancar();
        }   
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
