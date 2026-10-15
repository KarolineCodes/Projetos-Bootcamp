package petshop;

public class FabricaPet {

    public static Pet criar(String nome, String especie, double peso) {

        Pet pet;
        if (especie.equalsIgnoreCase("Cachorro")) {
            pet = new Cachorro();
        } else if (especie.equalsIgnoreCase("Gato")) {
            pet = new Gato();
        } else {
            pet = new Pet();
        }

        pet.nome = nome;
        pet.especie = especie;
        pet.peso = peso;

        pet.porte = pet.definirPorte();

        return pet;
    }
}
