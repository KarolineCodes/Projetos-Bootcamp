package petshop;


public class Banho implements Servico {
    public void executar(String nomeDoPet) {
        System.out.println("Dando banho em " + nomeDoPet + "...");
    }
}
