package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

import java.io.Serializable;

/*
 * Classe que representa um ingresso
 */
public abstract class Ingresso implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeShow;                            // Nome do show ao qual o ingresso permite a entrada
    private String nomeTurne;                           // Nome da turnê ao qual o show faz parte
    private String nomeComprador;                       // Nome do comprador do ingresso
    private double valor;                               // Valor do ingresso
    private String tipoIngresso;                        // Tipo do ingresso

    /*
     * Construtor da classe Ingresso
     */
    public Ingresso(String nomeShow, String nomeTurne, String nomeComprador, double valor, String tipoIngresso) {
        this.nomeShow = nomeShow;
        this.nomeComprador = nomeComprador;
        this.valor = valor;
        this.tipoIngresso = tipoIngresso;
        this.nomeTurne = nomeTurne;
    }


    /*
     * Retorna o nome do show ao qual o ingresso permite a entrada
     */
    public String getNomeShow() {
        return nomeShow;
    }

    /*
     * Retorna o nome da turnê da qual o show faz parte
     */
    public String getNomeTurne() {
        return nomeTurne;
    }

     /*
     * Retorna o nome do comprador do ingresso
     */
    public String getNomeComprador() {
        return nomeComprador;
    }

     /*
     * Retorna o preço do ingresso
     */
    public double getValorIngresso() {
        return valor;
    }

    /*
     * Retorna o tipo do ingresso
     */
    public String getTipoIngresso() {
        return tipoIngresso;
    }

}   
