package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;


public class Administracao {
    private ControladoraCidade controlCidade;
    private ControladoraShow controlShow;
    private ControladoraTurne controlTurne;


    // Construtor de Administracao
    public Administracao() {
        controlCidade = new ControladoraCidade();
        controlShow = new ControladoraShow();
        controlTurne = new ControladoraTurne();
    }


    /*
     * Cadastra uma cidade
     */
    public boolean cadastrarCidade(String nomeCidade, String nomeEstado) {
        return controlCidade.cadastrarCidade(nomeCidade, nomeEstado);    
    }

    /*
     * Retorna uma lista com as cidades cadastradas
     * Retorna null caso a cidade não exista
     */
    public String listarCidades() {
        String listaCidades = "";

        // Recebe a lista de cidades cadastradas
        listaCidades = controlCidade.listarCidades();

        // Verifica se a lista é vazia
        if(listaCidades.equals("")) {
            return null;
        }

        return listaCidades;
    }

    /*
     * Retorna uma String com a descrição completa da cidade
     * Retorna null caso a cidade não exista
     */
    public String detalharCidade(String nomeCidade) {
        String descricaoCidade = "";

        // Recebe a descrição da cidade
        descricaoCidade = controlCidade.detalharCidade(nomeCidade);

        // Verifica se a descrição é vazia
        if(descricaoCidade.equals("")) {
            return null;
        }
        return descricaoCidade;
    }

    /*
     * Remove uma cidade
     * Retorna uma String de acordo com o resultado da remoção
     */
    public boolean removerCidade(String nomeCidade) {
        // Verifica se a cidade foi cadastrada em algum show
        if(controlTurne.cidadeCadastradaEmShows(nomeCidade)) {
            return false;
        }

        // Tenta remover a cidade e retorna o resultado
        return controlCidade.removerCidade(nomeCidade); 
    }

    /*
     * Cadastra uma turnê
     */
    public boolean cadastrarTurne(String nomeTurne, String nomeBanda, String estiloMusical, String entidadePromotora) {
        return controlTurne.cadastrarTurne(nomeTurne, nomeBanda, estiloMusical, entidadePromotora);
    }

     /*
     * Retorna uma lista com as turnês cadastradas
     * Retorna null caso a turnê não exista
     */
    public String listarTurnes() {
        String listaTurnes = "";

        // Recebe a lista de turnês
        listaTurnes = controlTurne.listarTurnes();

        // Verifica se a lista é vazia
        if(listaTurnes.equals("")) {
            return null;
        }

        return listaTurnes;
    }

    /*
     * Retorna uma String com a descrição completa da turnê
     * Retorna null caso a turnê não exista
     */
    public String detalharTurne(String nomeTurne) {
        String descricaoTurne = "";

        // Recebe a descrição da turnê
        descricaoTurne = controlTurne.detalharTurne(nomeTurne);

        // Verifica se a descrição é vazia
        if(descricaoTurne.equals("")) {
            return null;
        }

        return descricaoTurne;
    }

     /*
     * Remove uma turnê
     */
    public boolean removerTurne(String nomeTurne) {
        return controlTurne.removerTurne(nomeTurne);
    }

    /*
     * Cadastra um show
     */
    public boolean cadastrarShow(String nomeShow, String nomeTurne, String nomeCidade, String dia, String horario, int maximoIngressos, String tipoDeShow) {

        // Verifica se a turnê informada existe
        if(!controlTurne.turneCadastrada(nomeTurne)){
            return false;
        }

        // Verifica se a cidade informada não está cadastrada
        if(!controlCidade.cidadeCadastrada(nomeCidade)) {
            return false;
        } 
        
        // Verifica se já existe um show cadastrado com a data informada
        if(controlTurne.existeShowNessaData(nomeTurne, dia, horario)){
            return false;
        }

        // Cadastra o show na lista de shows da classe ControladoraTurne
        controlTurne.cadastrarShow(nomeTurne, controlShow.criarShow(nomeShow, nomeTurne, controlCidade.getCidade(nomeCidade), dia, horario, maximoIngressos, tipoDeShow));

        return true;
    }

    /*
     * Retorna uma lista com o nome dos shows de uma turnê
     * Retorna null caso o show não exista
     */
    public String listarShows(String nomeTurne) {
        String listaShows = "";

        // Recebe a lista de shows da turnê
        listaShows = controlTurne.listarShows(nomeTurne);

        // Verifica se a lista é vazia
        if(listaShows.equals("")) {
            listaShows = null;
        }

        return listaShows;
    }

    /*
     * Retorna uma String com a descrição completa do show
     * Retorna null caso o show não exista
     */
    public String detalharShow(String nomeShow) {
        String descricaoShow = "";

        // Recebe a descrição do show
        descricaoShow = controlShow.detalharShow(controlTurne.getShow(nomeShow));

        // Verifica se a descrição é vazia
        if(descricaoShow.equals("")) {
            descricaoShow = null;
        }

        return descricaoShow;
    }

    /*
     * Remove um show
     */
    public boolean removerShow(String nomeShow) {
        // Tenta remover o show da lista de shows de ControladoraTurne e retorna o resultado
        return removerShowDeTurne(nomeShow);
    }
    
    /*
     * Remove um show da lista de shows da turnê
     */
    private boolean removerShowDeTurne(String nomeShow) {
        return controlTurne.removerShow(controlTurne.getNomeTurneShow(nomeShow), nomeShow);
    }   
}
