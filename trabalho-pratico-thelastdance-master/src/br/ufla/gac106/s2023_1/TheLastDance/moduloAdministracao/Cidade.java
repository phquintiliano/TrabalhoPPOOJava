package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

import java.io.Serializable;

public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeCidade;
    private String estado;

    // Construtor de cidade (local)
    public Cidade (String nomeCidade, String estado){
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }

    /*
     * Retorna o nome da cidade
     */
    public String getNomeCidade() {
        return nomeCidade;
    } 

    /*
     * Retorna o nome do estado
     */
    public String getNomeEstado() {
        return estado;
    } 
  
    /*
     * Retorna a descrição completa da cidade (nome e estado)
     */
    public String getDescricaoCompleta() {
        String descricaoCompleta = nomeCidade + " - " + estado;
        return descricaoCompleta;
    }


}
