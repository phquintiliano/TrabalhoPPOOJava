package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

/*
 * Classe que controla as modificações na lista de shows
 */
public class ControladoraShow {

    /* 
     * Construtor da classe ControladoraShow
     */
    public ControladoraShow() {
    }   

    /*
     * Cria e retorna um show
     * Retorna null se não for possível criá-lo
     */
    public Show criarShow(String nomeShow, String nomeTurne, Cidade cidade, String dia, String horario, int maximoIngressos, String tipoDeShow) {
        Show show;

        // Verifica se o show é de massa ou exclusivo para criar o objeto
        if(tipoDeShow.equals("massa")) {
            show = new ShowMassa(nomeShow, nomeTurne, cidade, dia, horario, maximoIngressos);
        } else if(tipoDeShow.equals("exclusivo")) {
            show = new ShowExclusivo(nomeShow, nomeTurne, cidade, dia, horario, maximoIngressos);
        } else {
            return null;
        }
        
        return show;
    }

    /*
     * Retorna uma String com a descrição do show
     */
    public String detalharShow(IShow show) {
        String descricao = "";

        // Verifica se o show não é nulo
        if(show != null) {
            descricao = "Nome da Turnê: " + show.getNomeTurne() + "\n"
                    + "Nome do show: " + show.getNomeShow() + "\n"
                    + "Cidade: " + show.getDescricaoCidade() + "\n"
                    + "Data: " + show.getDia() + " - " + show.getHorario() + "\n"
                    + "Preço do ingresso: R$" + show.getPrecoIngresso() + "\n"
                    + "Total de ingressos: " + show.getQtdIngressos();
        }

        return descricao;
    }

}
