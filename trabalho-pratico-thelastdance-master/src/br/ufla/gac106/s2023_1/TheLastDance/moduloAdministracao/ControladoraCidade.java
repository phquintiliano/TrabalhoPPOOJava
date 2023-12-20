package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

import java.util.HashMap;
import java.util.Map;
import br.ufla.gac106.s2023_1.TheLastDance.dados.DadosCidade;


public class ControladoraCidade  {
    private static Map<String, Cidade> cidades; // Lista com as cidades cadastradas (chave nomeCidade)

    /*
     * Construtor da classe ControladoraCidade
     */
    public ControladoraCidade() {
        carregarDadosArquivo();  
    }             

    /*
     * Carrega os dados do arquivo para a lista de cidades
     */
    private void carregarDadosArquivo() {
        cidades = DadosCidade.carregarCidades();
        
        // Inicializa o HashMap de cidades caso o arquivo esteja vazio
        if(cidades == null){
            cidades = new HashMap<String, Cidade>();
        }
    }

    /*
     * Cadastra uma cidade
     */
    public boolean cadastrarCidade(String nomeCidade, String estado) {
        Cidade cidade = criarCidade(nomeCidade, estado);

        // Se a cidade não for nula, adiciona à lista
        if (cidade != null) {
            cidades.put(cidade.getNomeCidade(), cidade);
            DadosCidade.salvarCidades(cidades);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Cria e retorna uma cidade (retorna null se já existir na lista)
     */
    private Cidade criarCidade(String nomeCidade, String estado) {
        if (!cidadeCadastrada(nomeCidade)) {
            Cidade cidade = new Cidade(nomeCidade, estado);
            return cidade;
        } else {
            return null;
        }
    }

    /*
     * Retorna uma String com o nome das cidades cadastradas
     */
    public String listarCidades() {
        StringBuilder lista = new StringBuilder();

        for (String nomeCidade : cidades.keySet()) {
            lista.append(nomeCidade).append("\n");
        }

        return lista.toString();
    }

    /*
     * Retorna uma String com a descrição da cidade
     */
    public String detalharCidade(String nomeCidade) {
        StringBuilder descricao = new StringBuilder();

        if (cidadeCadastrada(nomeCidade)) {
            descricao.append("Cidade: ").append(cidades.get(nomeCidade).getDescricaoCompleta());
        }

        return descricao.toString();
    }

    /*
     * Remove uma cidade se ela existir na lista
     */
    public boolean removerCidade(String nomeCidade) {
        if (cidadeCadastrada(nomeCidade)) {
            cidades.remove(nomeCidade);
            DadosCidade.salvarCidades(cidades);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Retorna true se a cidade informada existir no sistema
     */
    public boolean cidadeCadastrada(String nomeCidade) {
        return cidades.containsKey(nomeCidade);
    }

    /*
     * Retorna uma cidade da lista ou null caso não exista
     */
    public Cidade getCidade(String nomeCidade) {
        return cidades.get(nomeCidade);
    }
}