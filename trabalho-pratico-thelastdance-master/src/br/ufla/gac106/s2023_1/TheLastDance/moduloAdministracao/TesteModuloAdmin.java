package br.ufla.gac106.s2023_1.TheLastDance.moduloAdministracao;

public class TesteModuloAdmin {
    public static void main(String[] args) throws Exception {
        
        try {
            InterfaceUsuario iu = new InterfaceUsuario();
            iu.executar();
        } catch (RuntimeException e) {
            System.out.println("Programa finalizado com erro");
        }
        
    }
}
