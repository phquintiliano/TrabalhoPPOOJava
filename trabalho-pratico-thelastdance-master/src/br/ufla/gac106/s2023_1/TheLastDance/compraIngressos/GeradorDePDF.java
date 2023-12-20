package br.ufla.gac106.s2023_1.TheLastDance.compraIngressos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/*
 * Classe que gera um arquivo PDF com as informações da compra de ingressos
 */
public class GeradorDePDF {
    private Document document;                          // Documento PDF gerado
    private String nomeArquivo;                         // Nome do arquivo
    private ControladoraIngressos controlIngressos;     // Objeto que contém os ingressos a serem escritos no arquivo

    /*
     * Construtor da classe GeradorDePDF
     */
    public GeradorDePDF(String nomeArquivo, ControladoraIngressos controlIngressos) {
        this.nomeArquivo = nomeArquivo;
        document = new Document();
        this.controlIngressos = controlIngressos;
    }

    public boolean gerarPDF() {
        try {
            // Cria o escritor do documento
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            // Ajusta o tamanho da página
            document.setPageSize(PageSize.A4);
            // Abre o documento
            document.open();

            adicionarIngressos();

            // Fecha o documento
            document.close();

            return true;
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*
     * Adiciona o conteúdo dos ingressos ao arquivo
     */
    public void adicionarIngressos() {
        List<Ingresso> ingressos = controlIngressos.getIngressos();
            String descricaoCidade = controlIngressos.getDescricaoCidade();
            String dia = controlIngressos.getDia();
            String horario = controlIngressos.getHorario();

        try {
            for(int i = 0; i < ingressos.size(); i++) {
                Ingresso ingressoAtual = ingressos.get(i);

                document.add(new Paragraph("Ingresso para o show musical " + ingressoAtual.getNomeShow()));
                document.add(new Paragraph("Turnê " + ingressoAtual.getNomeTurne()));
                document.add(new Paragraph("Local: " + descricaoCidade));
                document.add(new Paragraph("Data: " + dia));
                document.add(new Paragraph("Horário: " + horario + "\n"));
                document.add(new Paragraph("Nome do comprador: " + ingressoAtual.getNomeComprador()));
                document.add(new Paragraph("Tipo de ingresso: " + ingressoAtual.getTipoIngresso()));
                document.add(new Paragraph("Valor: R$" + ControladoraIngressos.formatarDouble(ingressoAtual.getValorIngresso())));

                if(ingressos.size() > i)
                    document.newPage();
            }
        } catch (DocumentException e) {
             e.printStackTrace();
        }
    }
}

