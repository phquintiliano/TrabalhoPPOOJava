package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

import java.io.Serializable;

public class Show implements IShow , Serializable {  
    private static final long serialVersionUID = 1L; 
    private String nomeTurne;           // Nome da turne da qual o show faz parte
    private String nomeShow;            // Nome do show
    private Cidade cidade;              // Cidade onde o show será realizado
    private String dia;                 // dd/MM/yyyy - dia/mes/ano
    private String horario;             // 00:00 - formato 24h
    private Double precoIngresso;       // Preço do ingresso (definido nas subclasses)
    private int qtdIngressos;           // Quantidade de ingressos disponível

    // Construtor de Show (Atividade)
    public Show (String nomeShow, String nomeTurne, Cidade cidade, String dia, String horario, Double precoIngresso, int qtdIngressos){
        this.nomeShow = nomeShow;
        this.nomeTurne = nomeTurne;
        this.cidade = cidade;
        this.dia = dia;
        this.horario = horario;
        this.precoIngresso = precoIngresso;
        this.qtdIngressos = qtdIngressos;
    }

    /*
     * Retorna o nome do show
     */
    public String getNomeShow() {
        return nomeShow;
    }

    /*
     * Retorna o dia do show
     */
    public String getDia() {
        return dia;
    }

    /*
     * Retorna o horario do show
     */
    public String getHorario() {
        return horario;
    }

    /*
     * Retorna o nome da cidade
     */
    public String getNomeCidade() {
        return cidade.getNomeCidade();
    }

     /*
     * Retorna o nome do estado
     */
    public String getDescricaoCidade() {
        return cidade.getDescricaoCompleta();
    } 

     /*
     * Retorna o preço do ingresso
     */
    public Double getPrecoIngresso() {
        return precoIngresso;
    } 
    
    /*
     * Retorna o numero maximo de ingressos disponível para o show
     */
    public int getQtdIngressos() {
        return qtdIngressos;
    } 

    /*
     * Retorna o nome da turnê da qual o show faz parte
     */
    public String getNomeTurne() {
        return nomeTurne;
    }

    /*
     * Diminui a quantidade de ingressos de acordo com a quantidade informada
     * Retorna false se não houverem ingressos suficientes
     */
    public boolean comprarIngresso(int qtd) {
        if((qtdIngressos - qtd) >= 0) {
            qtdIngressos -= qtd;
            return true;
        } else {
            return false;
        }
    }

}
