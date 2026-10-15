package petshop;

// Questão 14: Gato é filha de Pet. Herda nome, espécie, peso e porte.
// L (Substituição de Liskov): pode ser usado em qualquer lugar que espera um Pet.
public class Gato extends Pet {
    @Override
    void emitirSom() {
        System.out.println(nome + " faz: Miau!");
    }
}
