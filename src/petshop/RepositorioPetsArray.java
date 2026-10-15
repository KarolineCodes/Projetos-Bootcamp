package petshop;

import java.util.HashSet;


public class RepositorioPetsArray implements RepositorioPets {
    private Pet[] lista;

    private int total = 0;

    public RepositorioPetsArray(int capacidade) {
        lista = new Pet[capacidade];
    }

    public void adicionar(Pet pet) {
        lista[total] = pet;
        total++;
    }

    public boolean estaCheio() {
        return total == lista.length;
    }

    public int total() {
        return total;
    }

    public Pet pegar(int posicao) {
        return lista[posicao];
    }


    public Pet buscarPorNome(String nome) {
        for (int i = 0; i < total; i++) {
            if (lista[i].nome.equalsIgnoreCase(nome)) {
                return lista[i];
            }
        }
        return null;
    }

    public HashSet<String> especiesCadastradas() {
        HashSet<String> especies = new HashSet<>();
        for (int i = 0; i < total; i++) {
            especies.add(lista[i].especie);
        }
        return especies;
    }
}
