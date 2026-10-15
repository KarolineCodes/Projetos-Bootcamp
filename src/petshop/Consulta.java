package petshop;


public class Consulta implements Servico {
    public void executar(String nomeDoPet) {
        System.out.println("Levando " + nomeDoPet + " para a consulta...");
    }
}
