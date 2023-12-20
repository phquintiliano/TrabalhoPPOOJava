package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

import java.util.Map;

/*
 * Interface que define métodos de consulta de uma turnê
 */
public interface ITurne {

    /*
     * Retorna o nome da turne
     */
    String getNomeTurne();

     /*
     * Retorna o nome da banda
     */
    String getNomeBanda();

     /*
     * Retorna o estilo musical 
     */
    String getEstiloMusical();

     /*
     * Retorna a entidade promotora
     */
    String getEntidadePromotora ();

    /*
     * Retorna o nome dos shows da turnê
     */
    String getNomeShows();

    /*
     * Retorna true se não houverem shows cadastrados
     */
    public boolean turneVazia();

     /*
     * Retorna uma lista com os shows cadastrados na turnê
     */
    public Map<String, Show> getShows();

    /* 
     * Retorna um show da lista de shows cadastrados na turnê
     */
    public IShow getShow(String nomeShow);
}
