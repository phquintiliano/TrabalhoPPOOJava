package br.ufla.gac106.s2023_1.TheLastDance.dados;

import br.ufla.gac106.s2023_1.TheLastDance.compraIngressos.Ingresso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.List;
import java.util.ArrayList;


public class DadosIngressos {
    private final static String nomeArquivo = "ingressos.arq";  // Nome do arquivo do qual os dados serão lidos e salvos

    /*
     * Salva a lista de ingressos vendidos no arquivo
     */
    public static void salvarIngressos(List<Ingresso> ingressosVendidos) {
        try {
            ObjectOutputStream objeto = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
            objeto.writeObject(ingressosVendidos);
            objeto.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /*
     * Carrega os dados do arquivo para a lista de ingressos vendidos e a retorna
     */
    @SuppressWarnings("unchecked")
    public static List<Ingresso> carregarIngressos() {
        try {
            File arquivo = new File(nomeArquivo);
            if (arquivo.exists() && !arquivo.isDirectory()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo));
                List<Ingresso> ingressosVendidos = (ArrayList<Ingresso>)ois.readObject();
                ois.close();
                return ingressosVendidos;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
