package br.ufla.gac106.s2023_1.TheLastDance.relatorios;

/*
 * Classe que define os tipos de identificadores de dados para gerar relatórios de ingressos
 */
public enum IdentificadorDados {
    TURNE("Turnê"), SHOW("Show"), COMPRADOR("Comprador");

    private String descricaoId;         // Descrição do identificador selecionado

    /*
     * Construtor da classe IdentificadorDados
     */
    IdentificadorDados(String descricaoId) {
        this.descricaoId = descricaoId;
    }

    /*
     * Retorna a descrição do identificador
     */
    public String getDescricaoId() {
        return descricaoId;
    }
}
