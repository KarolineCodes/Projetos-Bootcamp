package petshop;

import java.util.ArrayDeque;


public class FilaBanho {

    private ArrayDeque<String> fila = new ArrayDeque<>();

    public void entrar(String nomeDoPet) {
        fila.add(nomeDoPet);
    }

    public boolean estaVazia() {
        return fila.isEmpty();
    }


    public String verProximo() {
        return fila.peek();
    }


    public String atender() {
        return fila.poll();
    }

    @Override
    public String toString() {
        return fila.toString();
    }
}
