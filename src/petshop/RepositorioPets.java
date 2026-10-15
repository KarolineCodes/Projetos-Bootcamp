package petshop;

import java.util.HashSet;

public interface RepositorioPets {
    void adicionar(Pet pet);

    boolean estaCheio();

    int total();

    Pet pegar(int posicao);

    Pet buscarPorNome(String nome);

    HashSet<String> especiesCadastradas();
}
