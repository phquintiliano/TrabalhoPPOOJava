package br.ufla.gac106.s2023_1.TheLastDance.relatorios;

import br.ufla.gac106.s2023_1.TheLastDance.compraIngressos.Ingresso;
import br.ufla.gac106.s2023_1.TheLastDance.dados.DadosIngressos;

import java.util.ArrayList;
import java.util.List;


/*
 * Classe que gerencia os ingressos vendidos
 */
public class GerenciadorIngressosVendidos{
    private List<Ingresso> ingressosVendidos;           // Lista de ingressos vendidos

    /*
     * Construtor da classe GerenciadorIngressosVendidos
     */
    public GerenciadorIngressosVendidos() {
        carregarDadosArquivo();
    }

    /*
     * Carrega os dados do arquivo para a lista de ingressos vendidos
     */
    private void carregarDadosArquivo() {
        ingressosVendidos = DadosIngressos.carregarIngressos();
        
        // Inicializa o HashMap de ingressos vendidos caso o arquivo esteja vazio
        if(ingressosVendidos == null){
            ingressosVendidos = new ArrayList<Ingresso>();
        }
    }

    /*
     * Adiciona os novos ingressos vendidos à lista e salva no arquivo
     */
    public void adicionarIngressosVendidos(List<Ingresso> novosIngressosVendidos) {
        for(int i = 0; i < novosIngressosVendidos.size(); i++) {
            ingressosVendidos.add(novosIngressosVendidos.get(i));
        }

        salvarDadosArquivo();
    }

    /*
     * Método que, dado um identificador (turnê, show ou usuario), cria uma lista de ContabilizadorIngressos e a retorna
     */
    public List<ContabilizadorIngressos> getListaIngressosPorId(IdentificadorDados id) {
        List<ContIngressosPorId> contIngressosVendidos = new ArrayList<ContIngressosPorId>();
        // Lista com todos os identificadores contidos nos ingressos vendidos
        List<String> identificadores = pegarIdentificadores(id);       
        // Lista com os identificadores que são diferentes entre si 
        List<String> identificadoresDiferentes = new ArrayList<String>();   
        boolean novoIdentificador;

        
        for(int i = 0; i < ingressosVendidos.size(); i++) {
            novoIdentificador = true;
            for(int j = 0; j < identificadoresDiferentes.size(); j++) {
                if(identificadores.get(i).equals(identificadoresDiferentes.get(j))) {
                    contIngressosVendidos.get(j).adicionarIngresso(ingressosVendidos.get(i));
                    novoIdentificador = false;
                }
            }

            if(novoIdentificador) {
                identificadoresDiferentes.add(identificadores.get(i));
                contIngressosVendidos.add(new ContIngressosPorId(identificadores.get(i)));
                contIngressosVendidos.get(contIngressosVendidos.size()-1).adicionarIngresso(ingressosVendidos.get(i));
            }
        }
        return conveterContIngressos(contIngressosVendidos);
    }

    /*
     * Converte uma lista de ContIngressosPorId para uma lista de Contabilizador de ingressos
     */
    private List<ContabilizadorIngressos> conveterContIngressos(List<ContIngressosPorId> contIngressosporId) {
        List<ContabilizadorIngressos> contabilizadorIngressos = new ArrayList<ContabilizadorIngressos>();
        for(int i = 0; i < contIngressosporId.size(); i++) {
            contabilizadorIngressos.add(contIngressosporId.get(i));
        }
        return contabilizadorIngressos;
    }

    /*
     * Retorna uma lista com todos os identificadores contidos nos ingressos, de acordo com o tipo de identificador passado
     */
    private List<String> pegarIdentificadores(IdentificadorDados id) {
        List<String> identificadoresIngressos = new ArrayList<String>();

        if(id == IdentificadorDados.COMPRADOR) {
            for(int i = 0; i < ingressosVendidos.size(); i++) {
                identificadoresIngressos.add(ingressosVendidos.get(i).getNomeComprador());
            }
        } else if(id == IdentificadorDados.SHOW) {
            for(int i = 0; i < ingressosVendidos.size(); i++) {
                identificadoresIngressos.add(ingressosVendidos.get(i).getNomeShow());
            }
        } else if(id == IdentificadorDados.TURNE) {
            for(int i = 0; i < ingressosVendidos.size(); i++) {
                identificadoresIngressos.add(ingressosVendidos.get(i).getNomeTurne());
            }
        }

        return identificadoresIngressos;  
    }

    /*
     * Salva a lista de ingressos vendidos no arquivo
     */
    private void salvarDadosArquivo() {
        DadosIngressos.salvarIngressos(ingressosVendidos);
    }

}
