package br.ufla.gac106.s2023_1.TheLastDance.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao.Turne;

public class DadosTurne {
    private final static String nomeArquivo = "turne.arq";  // Nome do arquivo do qual os dados serão lidos e salvos

      /*
     * Salva a lista de turnês no arquivo
     */
    public static void salvarTurnes(Map<String, Turne> listaTurnes) {
        try {
            ObjectOutputStream objeto = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
            objeto.writeObject(listaTurnes);
            objeto.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /*
     * Carrega os dados do arquivo para a lista de turnês e a retorna
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Turne> carregarTurnes() {
        try {
            File arquivo = new File(nomeArquivo);
            if (arquivo.exists() && !arquivo.isDirectory()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo));
                HashMap<String, Turne> listaTurnes = (HashMap<String, Turne>)ois.readObject();
                ois.close();
                return listaTurnes;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
