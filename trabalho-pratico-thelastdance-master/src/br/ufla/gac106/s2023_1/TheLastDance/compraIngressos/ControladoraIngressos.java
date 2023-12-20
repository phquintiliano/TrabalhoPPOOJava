package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Show;
import br.ufla.gac106.s2023_1.TheLastDance.relatorios.GerenciadorIngressosVendidos;

public class ControladoraIngressos {
    private Show show;                                  // Show do qual os ingressos serão calculados
    private int qtdIngressoComum;                       // Quantidade de ingressos comuns a serem comprados
    private int qtdIngressoDesconto;                    // Quantidade de ingressos com desconto a serem comprados
    private int qtdMeiaEntrada;                         // Quantidade de ingressos meia entrada a serem comprados
    private double valorTotal;                          // Valor total da compra
    private List<Ingresso> ingressos;                   // Lista de ingressos
    
    /*
     * Construtor da classe ControladoraIngressos
     */
    public ControladoraIngressos(Show show) {
        if(show == null) {
            throw new NullPointerException();
        }

        this.show = show;
        ingressos = new ArrayList<Ingresso>();
    }

    /*
     * Retorna o preço do ingresso comum
     */
    public double getPrecoIngressoComum() {
        IngressoComum ingresso = new IngressoComum("admin", "admin", "admin", show.getPrecoIngresso());
        return ingresso.getValorIngresso();
    }

    /*
     * Retorna o preço do ingresso com desconto
     */
    public double getPrecoIngressoDesconto() {
        IngressoDesconto ingresso = new IngressoDesconto("admin", "admin", "admin", show.getPrecoIngresso());
        return ingresso.getValorIngresso();
    }

    /*
     * Retorna o preço do ingresso meia entrada
     */
    public double getPrecoIngressoMeiaEntrada() {
        IngressoMeia ingresso = new IngressoMeia("admin", "admin", "admin", show.getPrecoIngresso());
        return ingresso.getValorIngresso();
    }
    
    /*
     * Adiciona ingressos comuns à lista
     */
    public boolean adicionarIngressosComuns(int qtd, String nomeComprador) {
        if(qtd > 0) {
            for(int i = 0; i < qtd; i++) {
                ingressos.add(new IngressoComum(show.getNomeShow(), show.getNomeTurne(), nomeComprador, show.getPrecoIngresso()));
                qtdIngressoComum ++;
            }
            return true;
        } else {
            return false;
        }
    }

    /*
     * Adiciona ingressos com desconto à lista
     */
    public boolean adicionarIngressosDesconto(int qtd, String nomeComprador) {
        if(qtd > 0) {
            for(int i = 0; i < qtd; i++) {
                ingressos.add(new IngressoDesconto(show.getNomeShow(), show.getNomeTurne(), nomeComprador, show.getPrecoIngresso()));
                qtdIngressoDesconto ++;
            }
            return true;
        } else {
            return false;
        }
    }

    /*
     * Adiciona ingressos comuns à lista
     */
    public boolean adicionarIngressosMeia(int qtd, String nomeComprador) {
        if(qtd > 0) {
            for(int i = 0; i < qtd; i++) {
                ingressos.add(new IngressoMeia(show.getNomeShow(), show.getNomeTurne(), nomeComprador, show.getPrecoIngresso()));
                qtdMeiaEntrada ++;
            }
            return true;
        } else {
            return false;
        }
    }

    /*
     * Retorna a quantidade de ingressos comuns
     */
    public int getQtdIngressoComum() {
        return qtdIngressoComum;
    }

    /*
     * Retorna a quantidade de ingressos com desconto
     */
    public int getQtdIngressoDesconto() {
        return qtdIngressoDesconto;
    }

    /*
     * Retorna a quantidade de ingressos meia entrada
     */
    public int getQtdMeiaEntrada() {
        return qtdMeiaEntrada;
    }

    /*
     * Retorna a quantidade total de ingressos a serem comprados
     */
    public int getTotalIngressos() {
        return ingressos.size();
    }

    /*
     * Retorna o nome do show
     */
    public String getNomeShow() {
        return show.getNomeShow();
    }

    /*
     * Retorna o nome da turnê da qual o show faz pa
     */
    public String getNomeTurne() {
        return show.getNomeTurne();
    }

    /*
     * Retorna o local onde o show será realizado (cidade e estado)
     */
    public String getDescricaoCidade() {
        return show.getDescricaoCidade();
    }

    /*
     * Retorna o dia do show
     */
    public String getDia() {
        return show.getDia();
    }

    /*
     * Retorna o horário em que o show será realizado
     */
    public String getHorario() {
        return show.getHorario();
    }

    /*
     * Retorna a lista de ingressos
     */
    public List<Ingresso> getIngressos() {
        return Collections.unmodifiableList(ingressos);
    }

    /*
     * Subtrai a quantidade de ingressos comprados da quantidade de ingressos disponível
     */
    public boolean comprarIngressos() {
        return show.comprarIngresso(ingressos.size());
    }

    /*
     * Retorna true se houverem ingressos suficientes para o show
     */
    public boolean haIngressosSuficientes(int qtdComum, int qtdDesconto, int qtdMeia) {
        if(ingressos.size() <= show.getQtdIngressos()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Calcula o valor total da compra
     */
    private void calcularValorTotal() {
        for(int i = 0; i < ingressos.size(); i++) {
            valorTotal += ingressos.get(i).getValorIngresso();
        }
    }

    /*
     * Retorna o valorTotal da compra
     */
    public double getValorTotal() {
        calcularValorTotal();
        return valorTotal;
    }

    /*
     * Formata um valor double para 2 casas decimais e o retorna como String
     * Método utilizado para imprimir o valor formatado para o usuário
     */
    public static String formatarDouble(double valor) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(valor);
    }

    /*
     * Salva os dados de ingressos vendidos no arquivo
     */
    public void salvarIngressos() {
        GerenciadorIngressosVendidos giv = new GerenciadorIngressosVendidos();
        giv.adicionarIngressosVendidos(ingressos);
    }

    /*
     * Limpa os dados de ingressos salvos no objeto (Show continua com seu valor)
     * Utilizado ao retornar para a tela anterior
     */
    public void limparDadosIngressos() {
        qtdIngressoComum = 0;
        qtdIngressoDesconto = 0;
        qtdMeiaEntrada = 0;
        valorTotal = 0;
        ingressos.clear();
    }

}
