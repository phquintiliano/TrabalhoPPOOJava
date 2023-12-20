package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

/*
 * Interface que define métodos de consulta de um show
 */
public interface IShow {
    /*
     * Retorna o nome do show
     */
    public String getNomeShow();

    /*
     * Retorna o dia do show
     */
    public String getDia();

    /*
     * Retorna o horario do show
     */
    public String getHorario();

    /*
     * Retorna o nome da cidade
     */
    public String getNomeCidade();

     /*
     * Retorna o nome do estado
     */
    public String getDescricaoCidade();

     /*
     * Retorna o preço do ingresso
     */
    public Double getPrecoIngresso();
    
    /*
     * Retorna o numero maximo de ingressos disponível para o show
     */
    public int getQtdIngressos(); 

    /*
     * Retorna o nome da turnê da qual o show faz parte
     */
    public String getNomeTurne();

}
