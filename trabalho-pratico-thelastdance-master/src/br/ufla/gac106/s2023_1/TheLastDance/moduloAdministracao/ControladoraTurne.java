package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

import java.util.HashMap;
import java.util.Map;

import br.ufla.gac106.s2023_1.TheLastDance.dados.DadosTurne;

public class ControladoraTurne {
    private static Map<String, Turne> turnes;   // Lista de turnes cadastradas (chave nomeTurne)

    public ControladoraTurne() {
        carregarDadosArquivo();
    }   

     /*
     * Carrega os dados do arquivo para a lista de turnês
     */
    private void carregarDadosArquivo() {
        turnes = DadosTurne.carregarTurnes();
        
        // Inicializa o HashMap de turnes caso o arquivo esteja vazio
        if(turnes == null){
            turnes = new HashMap<String, Turne>();
        }
    }


    /*
     * Cadastra uma turne
     */
    public boolean cadastrarTurne(String nomeTurne, String nomeBanda, String estiloMusical, String entidadePromotora) {
        Turne turne = criarTurne(nomeTurne, nomeBanda, estiloMusical, entidadePromotora);
        
        // Se a turne não for nula, adiciona à lista
        if(turne != null) {
            turnes.put(turne.getNomeTurne(), turne);
            DadosTurne.salvarTurnes(turnes);
            return true;
        } else {
            return false;
        } 
    }

    /*
     * Cria e retorna uma turnê (retorna null se já existir na lista)
     */
    private Turne criarTurne(String nomeTurne, String nomeBanda, String estiloMusical, String entidadePromotora) {
        if(!turneCadastrada(nomeTurne)) {
            Turne turne = new Turne (nomeTurne, nomeBanda, estiloMusical, entidadePromotora);
            return turne;
        } else {
            return null;
        } 
    }

    /*
     * Cadastra um show na lista de shows da turnê informada
     */
    public boolean cadastrarShow(String nomeTurne, Show show) {
        if(!turneCadastrada(nomeTurne)) {
            return false;
        }

        if(turnes.get(nomeTurne).adicionarShow(show)) {
            DadosTurne.salvarTurnes(turnes);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Retorna uma String com o nome das turnês cadadastradas
     */
    public String listarTurnes() {
        String lista = "";

        for(String turne : turnes.keySet()) {
            lista += turne + "\n";
        }

        return lista;
    }


    /*
     * Retorna uma String com a lista de shows cadastrados em uma turnê
     */
    public String listarShows(String nomeTurne) {
        String lista = "";

        if(turneCadastrada(nomeTurne)) {
            lista = turnes.get(nomeTurne).getNomeShows();
        }

        return lista;
    }

     /*
     * Retorna uma String com a descrição da turnê
     */
    public String detalharTurne(String nomeTurne) {
        String descricao = "";

        if(turneCadastrada(nomeTurne)) {
            Turne turne = turnes.get(nomeTurne);

            descricao += "Turnê: " + turne.getNomeTurne() + "\n"
                    + "Banda: " + turne.getNomeBanda() + "\n"
                    + "Estilo musical: " + turne.getEstiloMusical() + "\n"
                    + "Entidade promotora: " + turne.getEntidadePromotora();
        }

        return descricao;
    }

     /*
     * Remove uma turnê se ela existir na lista e não possuir shows cadastrados
     */
    public boolean removerTurne(String nomeTurne) {
        
        // Verifica se a turnê não existe
        if(!turneCadastrada(nomeTurne)) {
            return false;
        }

        // Verifica se a turnê possui shows cadastrados
        if(!turneVazia(nomeTurne)) {
            return false;
        }
        
        // Remove a turnê
        turnes.remove(nomeTurne);
        DadosTurne.salvarTurnes(turnes);
        return true;
    }

    /*
     * Remove um show da lista de shows da turnê informada
     */
    public boolean removerShow(String nomeTurne, String nomeShow) {
        if(turneCadastrada(nomeTurne)) {
            if(turnes.get(nomeTurne).removerShow(nomeShow)) {
                DadosTurne.salvarTurnes(turnes);
                return true;
            }
        }  

        return false;
    }

    /*
     * Retorna true se a turne informada estiver cadastrada na lista
     */
    public boolean turneCadastrada(String nomeTurne) {
        return turnes.containsKey(nomeTurne);
    }

    /*
     * Retorna true se a turnê passada não possuir shows cadastrados
     */
    public boolean turneVazia(String nomeTurne) {
        if(turnes.containsKey(nomeTurne)) {
            return turnes.get(nomeTurne).turneVazia();
        } else {
            return false;
        }
    }

    /*
     * Retorna true se já existir um show cadastrado para essa turnê com a data e hora informados
     */
    public boolean existeShowNessaData(String nomeTurne, String dia, String horario) {
        boolean existeUmShow = false;

        if(turneCadastrada(nomeTurne)) {
            existeUmShow = turnes.get(nomeTurne).existeUmShowNessaData(dia, horario);
        }

        return existeUmShow;
    }

    /*
     * Retorna o nome da turnê que contém o show informado
     */
    public String getNomeTurneShow(String nomeShow) {
        for(String nomeTurne : turnes.keySet()) {
            if(turnes.get(nomeTurne).showCadastrado(nomeShow)) {
                return nomeTurne;
            }
        }
        return null;
    }

    /*
     * Retorna true se a cidade informada estiver cadastrada em algum show da lista
     */
    public boolean cidadeCadastradaEmShows(String nomeCidade) {
        for(String nomeTurne : turnes.keySet()) {
            Turne turneAtual = turnes.get(nomeTurne);
            for(String nomeShow : turneAtual.getShows().keySet()) {
                if(turneAtual.getShow(nomeShow).getNomeCidade().equals(nomeCidade)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Retorna um show da lista de shows cadastrados na turnê
     */
    public IShow getShow(String nomeShow) {
        String nomeTurne = getNomeTurneShow(nomeShow);
        
        if(nomeTurne == null) {
            return null;
        }

        return turnes.get(nomeTurne).getShow(nomeShow);
    }
}
