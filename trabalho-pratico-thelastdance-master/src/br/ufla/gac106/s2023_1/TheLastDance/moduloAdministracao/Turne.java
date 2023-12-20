package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Turne implements ITurne, Serializable{
    private static final long serialVersionUID = 1L;    
    private HashMap<String, Show> shows;            // Lista de shows presentes na turnê (chave nome do show)
    private String nomeTurne;                       // Nome da turnê
    private String nomeBanda;                       // Nome da banda ou cantor
    private String estiloMusical;                   // Estilo musical apresentado
    private String entidadePromotora;               // Entidade que promove o evento

    // Construtor de turne (Evento)
    public Turne (String nomeTurne, String nomeBanda, String estiloMusical, String entidadePromotora){
        this.nomeTurne = nomeTurne;
        this.nomeBanda = nomeBanda;
        this.estiloMusical = estiloMusical;
        this.entidadePromotora = entidadePromotora;

        shows = new HashMap<String, Show>();
    }

    /*
     * Retorna o nome da turne
     */
    public String getNomeTurne () {
        return nomeTurne;
    }

     /*
     * Retorna o nome da banda
     */
    public String getNomeBanda() {
        return nomeBanda;
    }

     /*
     * Retorna o estilo musical 
     */
    public String getEstiloMusical() {
        return estiloMusical;
    }

     /*
     * Retorna a entidade promotora
     */
    public String getEntidadePromotora () {
        return entidadePromotora;
    }

    /*
     * Retorna o nome dos shows da turnê
     */
    public String getNomeShows() {
        String nomeShows = "";

        for(String show : shows.keySet()) {
            nomeShows += show + "\n";
        }

        return nomeShows;
    }

    /*
     * Adiciona um show a lista de shows
     */
    public boolean adicionarShow(Show show) {
        if(!showCadastrado(show.getNomeShow())) {
            shows.put(show.getNomeShow(), show);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Remove um show da lista de shows
     */
    public boolean removerShow(String nomeShow) {
        if(showCadastrado(nomeShow)) {
            shows.remove(nomeShow);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Retorna true se o show existir na lista de shows
     */
    public boolean showCadastrado(String nomeShow) {
        return shows.containsKey(nomeShow);
    }

    /*
     * Retorna true se não houverem shows cadastrados
     */
    public boolean turneVazia() {
        return shows.isEmpty();
    }

    /*
     * Retorna true se já existir um show cadastrado com a data informada
     */
    public boolean existeUmShowNessaData(String dia, String horario) {
        boolean existeUmShow = false;
        
        for(String nomeShow : shows.keySet()) {
            if(shows.get(nomeShow).getDia().equals(dia)) {
                if(shows.get(nomeShow).getHorario().equals(horario)) {
                   existeUmShow = true;
                }
            }
        }
        return existeUmShow;
    }

    /*
     * Retorna uma lista com os shows cadastrados na turnê
     */
    public Map<String, Show> getShows() {
        return Collections.unmodifiableMap(shows);
    }

    /*
     * Retorna um show da lista (apenas métodos de consulta)
     */
    public IShow getShow(String nomeShow) {
        return shows.get(nomeShow);
    }
}
