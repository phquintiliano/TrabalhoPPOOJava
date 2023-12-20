package br.ufla.gac106.s2023_1.TheLastDance.relatorios;

import br.ufla.gac106.s2023_1.TheLastDance.compraIngressos.Ingresso;

/*
 * Classe utilizada para contabilizar os ingressos vendidos dada um identificador pelo qual os dados serão organizadoss
 */
public class ContIngressosPorId implements ContabilizadorIngressos{
    private String identificador;              // Identificador 
    private int quantidadeIngressos;       // Quantidade de ingressos vendidos por identificador
    private double valorTotal;           // Valor total arrecadado por identificador

    /*
     * Construtor da classe ContIngressosPorId
     */
    public ContIngressosPorId(String identificador) {
        this.identificador = identificador;
    }

    /*
     * Retorna o identificador
     */
    public String identificador() {
        return identificador;
    }

    /**
     * Retorna a quantidade de ingressos vendidos
     */
    public int quantidadeIngressos() {
        return quantidadeIngressos;
    }

    /**
     * Valor total arrecadado com a venda de ingressos
     */
    public double valorTotal() {
        return valorTotal;
    }

    /*
     * Soma 1 a quantidade de ingressos vendidos
     */
    public void incrementarQtdIngressos() {
        quantidadeIngressos++;
    }

    /*
     * Soma o valor informado ao valor total arrecadado
     */
    public void somarValor(double valor) {
        valorTotal += valor;
    }

    /*
     * Incrementa a quantidade de ingressos e soma seu valor ao total arrecadado
     */
    public void adicionarIngresso(Ingresso ingresso) {
        incrementarQtdIngressos();
        valorTotal += ingresso.getValorIngresso();
    }
}
