package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;
import java.util.Scanner;

/* Classe responsável por exibir as opções de menu e tratar as opções escolhidas pelo usuário chamando os 
métodos correspondentes */
public class InterfaceUsuario {
    private Scanner entrada;
    private Administracao adm;
    
    public InterfaceUsuario() {
        entrada = new Scanner(System.in);
        try {
            adm = new Administracao();
        } catch(RuntimeException e) {
            System.out.println("Erro ao carregar os arquivos");
            throw new RuntimeException(e);
        }
    }
    
    /*
     * Método que executa um loop exibindo o menu e tratando a opção escolhida
     */
    public void executar() {
        int opcao;

        do {
            exibirMenu();
            opcao = Integer.parseInt(pedirString("\nEscolha uma opção"));
            separarTela();
            tratarMenu(opcao);

        } while (opcao != 0);

        // Fecha o objeto Scanner
        entrada.close();
    }

    /*
     * Método para exibir a decrição do menu de opções do usuário
    */
    private void exibirMenu() {

        separarTela();
        System.out.println("\tSistema de Administração de Shows Musicais");
        separarTela();

        System.out.println("");
        System.out.println("(1) - Cadastrar");
        System.out.println("(2) - Listar");
        System.out.println("(3) - Detalhar");
        System.out.println("(4) - Remover");
        System.out.println("(0) - Sair");
    }

    /*
     * Método para tratar as opções do menu
     */
    private void tratarMenu(int opcao) {
        switch (opcao) {
            case 1:
                cadastrar();
                break;
            case 2:
                listar();
                break;
            case 3:
                detalhar();
                break;
            case 4:
                remover();
                break;
            case 0:
                System.out.println("Programa encerrado!");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        // Pausa o programa até que o usuário pressione alguma tecla
        if(opcao != 0) {
            System.out.println("\nPressione ENTER para continuar");
            separarTela();
            entrada.nextLine();
        }
    }

    /*
     * Método para exibir as opções de informação a serem modificadas
     */
    private void exibirOpcoes() {
        System.out.println("");
        System.out.println("(1) - Cidade");
        System.out.println("(2) - Turnê");
        System.out.println("(3) - Show");
        System.out.println("(0) - Voltar ao menu");
    }

    /*
     * Método para exibir e tratar as opções de informações a serem modificadas
     * Retorna a opção digitada
     */
    private int tratarOpcoes() {
        int info;

        do {
            exibirOpcoes();

            info = Integer.parseInt(pedirString("Escolha o tipo de informação"));

            if(info != 0 && info != 1 && info != 2 && info != 3) {
                System.out.println("Digite uma opção válida!");
            }

        } while(info != 0 && info != 1 && info != 2 && info != 3);

        separarTela();
        return info;
    }
    
    /*
     * Método que chama os demais métodos de cadastro de informações no sistema imprime o resultado
     */
    private void cadastrar() {
        int informacao;
        String resultado = "";

        // Chama o método que exibe e trata as opções
        informacao = tratarOpcoes();
           
        if(informacao == 1) {  
            System.out.println("Cadastrando cidade \n");

            // Verifica se a cidade foi cadastrada
            if(cadastrarCidade()){
                resultado = "Cidade cadastrada com sucesso.";
            } else{
                resultado = "Não foi possível cadastrar a cidade. Já existe uma cidade cadastrada com esse nome";
            }

        } else if(informacao == 2) {
            System.out.println("Cadastrando turnê \n");

            // Verifica se a turnê foi cadastrada
            if(cadastrarTurne()){
                resultado = "Turnê cadastrada com sucesso";
            } else{
                resultado = "Já existe uma turnê cadastrada com o nome informado.";
            }

        } else if(informacao == 3) {
            System.out.println("Cadastrando show \n");

            // Verifica se o show foi cadastrado
            if(cadastrarShow()){
                resultado = "Show cadastrado com sucesso";
            } else {
                resultado =  "\n Nao foi possivel cadastrar o Show. Possiveis erros: \n " +
                             "\t 1 - Já existe um show cadastrado com esse nome \n" +
                             "\t 2 - A cidade informada não está cadastrada no sistema \n" +
                             "\t 3 - A turnê informada não está cadastrada no sistema \n " +
                             "\t 4 - Já existe um show cadastrado com a mesma data e horário nessa turnê";
            }
        } else {
            resultado = "Retornando ao menu";
        }

        System.out.println(resultado);
    }

    /*
     * Tenta cadastrar uma cidade e retorna o resultado
     */
    private boolean cadastrarCidade() { 

        // Pede ao usuário e armazena os dados necessários para cadastrar uma cidade
        String nomeCidade = pedirString("Nome da cidade"); 
        String estado = pedirString("Estado");

        // Retorna o resultado do cadastro
        return adm.cadastrarCidade(nomeCidade, estado);
    }

   /*
     * Tenta cadastrar uma turnê e retorna o resultado
     */
    private boolean cadastrarTurne() {

        // Pede ao usuário e armazena os dados necessários para cadastrar uma turnê
        String nomeTurne = pedirString("Nome da turnê"); 
        String nomeBanda = pedirString("Nome da Banda"); 
        String estiloMusical = pedirString("Estilo musical"); 
        String entidadePromotora = pedirString("Entidade promotora");

        // Retorna o resultado do cadastro
        return adm.cadastrarTurne(nomeTurne, nomeBanda, estiloMusical, entidadePromotora);
    }

    /*
     * Tenta cadastrar um show e retorna o resultado
     */
    private boolean cadastrarShow() {
        
        // Pede ao usuário e armazena os dados necessários para cadastrar um show
        String nomeShow = pedirString("Nome do Show");
        String nomeTurne = pedirString("Nome da Turnê");
        String nomeCidade = pedirString("Nome da Cidade");
        String dia = pedirString("Data no formato dia/mês/ano)");
        String horario = pedirString("Horário no formato horas:minutos");
        int maximoIngressos = Integer.parseInt(pedirString("Quantidade de ingressos disponíveis"));
        String tipoDeShow = tratarTipoShow(Integer.parseInt(pedirString("Tipo de show: (1) - massa ou (2) - exclusivo")));

        // Retorna o resultado do cadastro
        return adm.cadastrarShow(nomeShow, nomeTurne, nomeCidade, dia, horario, maximoIngressos, tipoDeShow);
    }

    /*
     * Retorna o nome correspondente ao tipo de show informado
     */ 
    private String tratarTipoShow(int tipo){
        if(tipo == 1){
           return "massa";
        }
        if(tipo == 2){
          return "exclusivo";    
        }

        return "";
    }


    /*
     * Método que chama os demais métodos de listagem de informações imprime o resultado
     */
    private void listar() {
        int informacao;
        String resultado = "";

        informacao = tratarOpcoes();
       
        if(informacao == 1) {         
            resultado = listarCidades();

            System.out.println("Lista de cidades \n");

            if(resultado == null){
                resultado = "Não existem cidades cadastradas no sistema";
            }
            
        } else if(informacao == 2) {

            resultado = listarTurnes();

            System.out.println("Lista de turnês \n");

            if(resultado == null){
                resultado = "Não existem turnês cadastradas no sistema";
            }

        } else if(informacao == 3) {

            resultado = listarShows();

            System.out.println("Lista de shows \n");

            if(resultado == null){
                resultado = "Não existem shows cadastrados para essa turnê";
            }

        } else {
            resultado = "Retornando ao menu";
        }
        
        System.out.println(resultado);
    }

    /*
     * Retorna uma String com as cidades cadastradas
     */
    private String listarCidades(){
        return adm.listarCidades();
    }

    /*
     * Retorna uma String com as turnês cadastradas
     */
    private String listarTurnes(){
        return adm.listarTurnes();
    }

    /*
     * Retorna uma String com os shows cadastrados em uma turnê
     */
    private String listarShows(){
        String nomeTurne = pedirString("Digite o nome da turne da qual você deseja ver os shows");
        return adm.listarShows(nomeTurne);
    }

    /*
     * Método que chama os demais métodos de detalhamento informações e imprime o resultado
     */
    private void detalhar() {
        int informacao;
        String resultado = "";

        informacao = tratarOpcoes();
       
        if(informacao == 1) {      
            System.out.println("Detalhando cidade \n");  

            resultado = detalharCidade();
            if(resultado == null){
              resultado =  "A cidade não está cadastrada no sistema";
            }  

        } else if(informacao == 2) {
            System.out.println("Detalhando turnê \n");

            resultado = detalharTurne();
            if(resultado == null){
                resultado = "A turnê não está cadastrada no sistema";
            }

        } else if(informacao == 3) {
            System.out.println("Detalhando show \n");

            resultado = detalharShow();
            if(resultado == null){
                resultado = "O show não está cadastrado no sistema";
            }

        } else {
            resultado = "Retornando ao menu";
        }   

        System.out.println(resultado);
    }

    /*
     * Retorna uma String com a descrição da cidade
     */
    private String detalharCidade(){
        String nomeCidade = pedirString("Nome da cidade"); 
        return adm.detalharCidade(nomeCidade);
    }

    /*
     * Retorna uma String com a descrição da turnê
     */
    private String detalharTurne(){
        String nomeTurne = pedirString("Nome da turnê");
        return adm.detalharTurne(nomeTurne);
    }

    /*
     * Retorna uma String com a descrição do show
     */
    private String detalharShow(){
        String nomeShow = pedirString("Nome do Show"); 
        return adm.detalharShow(nomeShow);
    }

    /*
     * Método que chama os demais métodos de remoção de informações e imprime o resultado
     */
    private void remover() {
        int informacao;
        String resultado = "";

        informacao = tratarOpcoes();
       
        if(informacao == 1) { 
            System.out.print("Removendo cidade \n"); 

            // Verifica se a cidade foi removida
            if(removerCidade()){
                resultado = "Cidade removida do sistema."; 
            } else {
                resultado = "Não foi possível remover a cidade. Possíveis erros: \n" + 
                            "\t A cidade está cadastrada em um ou mais shows \n" +
                            "\t A cidade não está cadastrada no sistema";
            }   

        } else if(informacao == 2) {
            System.out.print("Removendo turnê \n");

            // Verifica se a turnê foi removida
            if(removerTurne()){
                resultado = "Turnê removida com sucesso";
            } else {
                resultado = "Não foi possível remover a turnê. Possíveis erros: \n" + 
                            "\t A turnê possui shows cadastrados \n" +
                            "\t A turnê não está cadastrada no sistema";
            }

        } else if(informacao == 3) {
            System.out.print("Removendo show \n");

            // Verifica se o show foi removido
            if(removerShow()){
                resultado = "Show removido com sucesso.";
            } else {
                resultado = "O show não está cadastrado no sistema";
            }

        } else {
            resultado = "Retornando ao menu";
        }

        System.out.println(resultado);
    }

    /*
     * Tenta remover uma cidade da lista e retorna o resultado
     */
    private boolean removerCidade(){
        String nomeCidade = pedirString("Nome da cidade"); 
        return adm.removerCidade(nomeCidade);
    }

    /*
     * Tenta remover uma turnê da lista e retorna o resultado
     */
    private boolean removerTurne(){
        String nomeTurne = pedirString("Nome da turnê");
        return adm.removerTurne(nomeTurne);
    }

    /*
     * Tenta remover um show da lista e retorna o resultado
     */
    private boolean removerShow(){
        String nomeShow = pedirString("Nome do show"); 
        return adm.removerShow(nomeShow);
    }

    /*
     * Método auxiliar que imprime uma instrução ao usuário e retorna a informação digitada por ele
     */
    private String pedirString(String instrucao) {
        String informacao = "";
        
        System.out.print(instrucao + ": ");
        informacao = entrada.nextLine();

        return informacao;
    }
 
    /*
     * Imprime uma separação de tela
     */
    private void separarTela() {
        System.out.println("---------------------------------------------------------");
    }
}
