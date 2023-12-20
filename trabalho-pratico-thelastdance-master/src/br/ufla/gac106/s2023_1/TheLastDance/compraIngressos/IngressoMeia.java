package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

/*
 * Classe que representa um ingresso comum
 */
public class IngressoMeia extends Ingresso{
    private double valorFinal;                          // Valor final do ingresso, calculando suas adições e/ou descontos
    private static double taxaAdministracao = 12.50;    // Taxa de adiministração adicionada ao preço final dos ingressos

    /*
     * Construtor da classe IngressoComum
     */
    public IngressoMeia(String nomeShow, String nomeTurne, String nomeComprador, double valor) {
        super(nomeShow, nomeTurne, nomeComprador, valor, "ingresso meia entrada");
        calcularValorIngresso();
    }

    /*
     * Calcula o valor final do ingresso
     */
    private void calcularValorIngresso() {
        valorFinal = (super.getValorIngresso() * 0.5) + taxaAdministracao;
    }

    /*
     * Retorna o valor final do ingresso
     */
    public double getValorIngresso() {
        return valorFinal;
    }
}
