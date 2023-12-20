package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.ITurne;
import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Turne;

import br.ufla.gac106.s2023_1.TheLastDance.dados.DadosTurne;

import java.util.HashMap;
import java.util.Map;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


/*
 * Classe que exibe ao usuário a lista de eventos cadastrados no sistema
 */
public class JanelaEventos extends JanelaBase{
    private Map<String, Turne> turnes;                 // Lista  de turnes cadastradas no sistema - A interface ITurne permite acesso apenas aos métodos de leitura da classe Turne
    private ITurne turne;                               // Turne selecionada pelo usuario na caixa de seleção
    private JComboBox<String> caixaSelecaoTurnes;       // Caixa de seleção de turnês
    private JPanel painelCentral;                       // Painel central da janela

    
    /*
     * Construtor da classe JanelaEventos
     */
    public JanelaEventos(JanelaBase janelaAnterior) {
        super("Turnes", "Selecione um evento para visualizar suas informações", 1024, 768, true, janelaAnterior, true, true);
        inicializarTurnes();
        atualizarPainelCentral();
    }

    /*
     * Inicializa a lista de turnês
     */
    private void inicializarTurnes() {
        turnes = DadosTurne.carregarTurnes();
        if(turnes == null) {
            turnes = new HashMap<String, Turne>();
        }
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
        criarComponentes();
        painelCentral.add(caixaSelecaoTurnes);

        atualizarCaixaSelecaoTurnes();
    }

    /*
     * Cria os componentes do painel central
     */
    private void criarComponentes() {
        caixaSelecaoTurnes = new JComboBox<String>();

        // adiciona o método que tratará o evento de clique na caixa de seleção de Turnes
        caixaSelecaoTurnes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tratarSelecaoTurne();
            }
        });
    }

    /*
     * Trata a opção selecionada na caixa de seleção de turnês
     */
    private void tratarSelecaoTurne() {
        String turneSelecionada = caixaSelecaoTurnes.getSelectedItem() + "";
        for(String nomeTurne : turnes.keySet()) {
            if(nomeTurne.equals(turneSelecionada)) {
                turne = turnes.get(nomeTurne);
            }       
        }
    }

    /*
     * Atualiza a caixa de seleção de turnes com o nome de todas as turnes existentes na lista
     */
    private void atualizarCaixaSelecaoTurnes() {
        caixaSelecaoTurnes.removeAllItems();

        if(turnes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não existem turnês cadastradas no sistema!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for(String nomeTurne : turnes.keySet()) {
                caixaSelecaoTurnes.addItem(nomeTurne);
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
        // Verifica se existem turnês cadastradas no sistema
        if(turnes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não existem turnês cadastradas no sistema!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        // Avança para a próxima janela;
        JanelaAtividades jAtividades = new JanelaAtividades(this, turnes, turne.getNomeTurne());
        jAtividades.exibir();
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
