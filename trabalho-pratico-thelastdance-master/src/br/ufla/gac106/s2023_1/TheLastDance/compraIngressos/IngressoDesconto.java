package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

/*
 * Classe que representa um ingresso comum
 */
public class IngressoDesconto extends Ingresso{
    private double valorFinal;                          // Valor final do ingresso, calculando suas adições e/ou descontos
    private static double taxaAdministracao = 12.50;    // Taxa de adiministração adicionada ao preço final dos ingressos
    private static double desconto = 0.2;               // Valor do desconto no ingresso com relação ao preço inicial

    /*
     * Construtor da classe IngressoComum
     */
    public IngressoDesconto(String nomeShow, String nomeTurne, String nomeComprador, double valor) {
        super(nomeShow, nomeTurne, nomeComprador, valor, "ingresso com desconto");
        calcularValorIngresso();
    }

    /*
     * Calcula o valor final do ingresso
     */
    private void calcularValorIngresso() {
        valorFinal = (super.getValorIngresso() * (1 - desconto)) + taxaAdministracao;
    }

    /*
     * Retorna o valor final do ingresso
     */
    public double getValorIngresso() {
        return valorFinal;
    }
}
