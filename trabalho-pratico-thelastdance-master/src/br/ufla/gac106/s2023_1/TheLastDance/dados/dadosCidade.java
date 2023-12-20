package br.ufla.gac106.s2023_1.TheLastDance.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Cidade;

public class DadosCidade {
    private final static String nomeArquivo = "cidade.arq";  // Nome do arquivo do qual os dados serão lidos e salvos

    /*
     * Salva a lista de cidades no arquivo
    */
    public static void salvarCidades(Map<String, Cidade> listaCidades) {
        try {
            ObjectOutputStream objeto = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
            objeto.writeObject(listaCidades);
            objeto.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /*
     * Carrega os dados do arquivo para a lista de cidades e a retorna
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Cidade> carregarCidades() {
        try {
            File arquivo = new File(nomeArquivo);
            if (arquivo.exists() && !arquivo.isDirectory()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo));
                HashMap<String, Cidade> listaCidades = (HashMap<String, Cidade>)ois.readObject();
                ois.close();
                return listaCidades;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
