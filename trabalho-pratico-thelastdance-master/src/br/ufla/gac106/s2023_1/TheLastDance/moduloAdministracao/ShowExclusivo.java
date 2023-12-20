package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

public class ShowExclusivo extends Show  {

    // Construtor de showExclusivo (Tipos de Atividades)
    public ShowExclusivo(String nomeShow, String nomeTurne, Cidade cidade, String dia, String horario, int maximoIngressos){
        super(nomeShow, nomeTurne, cidade, dia, horario, 500.99, maximoIngressos);

    }
}
