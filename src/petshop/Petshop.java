package petshop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Petshop {
    private RepositorioPets pets;
    private FilaBanho fila;
    private ArrayList<Servico> servicos;
    private Scanner sc;

    public Petshop(RepositorioPets pets, FilaBanho fila, ArrayList<Servico> servicos, Scanner sc) {
        this.pets = pets;
        this.fila = fila;
        this.servicos = servicos;
        this.sc = sc;
    }

    public static void main(String[] args) {

        RepositorioPets pets = new RepositorioPetsArray(5);
        FilaBanho fila = new FilaBanho();


        ArrayList<Servico> servicos = new ArrayList<>();
        servicos.add(new Banho());
        servicos.add(new Tosa());
        servicos.add(new Consulta());

        Scanner sc = new Scanner(System.in);
        Petshop petshop = new Petshop(pets, fila, servicos, sc);
        petshop.iniciar();
        sc.close();
    }


    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            mostrarMenu();


            try {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarPet();
                        break;
                    case 2:
                        listarPets();
                        break;
                    case 3:
                        buscarPet();
                        break;
                    case 4:
                        mostrarEspecies();
                        break;
                    case 5:
                        entrarNaFila();
                        break;
                    case 6:
                        atenderProximo();
                        break;
                    case 7:
                        executarServicos();
                        break;
                    case 8:
                        verFicha();
                        break;
                    case 0:
                        System.out.println("Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=== PETSHOP ===");
        System.out.println("1 - Cadastrar pet");
        System.out.println("2 - Listar pets");
        System.out.println("3 - Buscar pet pelo nome");
        System.out.println("4 - Espécies cadastradas");
        System.out.println("5 - Entrar na fila de banho");
        System.out.println("6 - Atender próximo da fila");
        System.out.println("7 - Executar serviços");
        System.out.println("8 - Ver ficha do pet");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }


    private void cadastrarPet() {
        if (pets.estaCheio()) {
            System.out.println("Cadastro cheio!");
            return;
        }

        System.out.print("Nome do pet: ");
        String nome = sc.nextLine();

        System.out.print("Espécie: ");
        String especie = sc.nextLine();

        System.out.print("Peso (kg): ");
        double peso = sc.nextDouble();
        sc.nextLine();

        Pet novoPet = FabricaPet.criar(nome, especie, peso);
        pets.adicionar(novoPet);

        System.out.println();
        mostrarFicha(novoPet, "=== FICHA DO PET ===");
        System.out.println("Pet cadastrado! Agora são " + pets.total() + " pet(s).");
    }


    private void listarPets() {
        if (pets.total() == 0) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }

        System.out.println("\n--- PETS CADASTRADOS ---");
        for (int i = 0; i < pets.total(); i++) {
            Pet pet = pets.pegar(i);
            System.out.printf("%s (%s) - %.1f kg - Porte %s\n", pet.nome, pet.especie, pet.peso, pet.porte);

            pet.emitirSom();
        }
    }


    private void buscarPet() {
        System.out.print("Qual pet você procura? ");
        String busca = sc.nextLine();

        Pet encontrado = pets.buscarPorNome(busca);
        if (encontrado != null) {
            System.out.printf("Encontrado: %s (%s) - %.1f kg\n", encontrado.nome, encontrado.especie, encontrado.peso);
        } else {
            System.out.println("Pet não encontrado.");
        }
    }


    private void mostrarEspecies() {
        HashSet<String> especies = pets.especiesCadastradas();

        System.out.println("\n--- ESPÉCIES CADASTRADAS ---");
        for (String especie : especies) {
            System.out.println("- " + especie);
        }
        System.out.println("Total de espécies diferentes: " + especies.size());
    }


    private void entrarNaFila() {
        System.out.print("Nome do pet pra entrar na fila: ");
        String nome = sc.nextLine();

        fila.entrar(nome);
        System.out.println(nome + " entrou na fila.");
        System.out.println("Fila agora: " + fila);
    }


    private void atenderProximo() {
        if (fila.estaVazia()) {
            System.out.println("A fila está vazia.");
            return;
        }

        System.out.println("Próximo da fila: " + fila.verProximo());
        String atendido = fila.atender();
        System.out.println("Atendendo " + atendido + "...");
        System.out.println("Fila agora: " + fila);
    }

    private void executarServicos() {
        System.out.print("Nome do pet: ");
        String nome = sc.nextLine();

        for (Servico servico : servicos) {
            servico.executar(nome);
        }
    }


    private void verFicha() {
        System.out.print("Qual pet? ");
        String nome = sc.nextLine();

        Pet pet = pets.buscarPorNome(nome);
        if (pet == null) {
            System.out.println("Pet não encontrado.");
            return;
        }

        mostrarFicha(pet);
        System.out.println("---");
        mostrarFicha(pet, "=== FICHA DO PET ===");
    }


    static void mostrarFicha(Pet pet) {
        System.out.println("Nome: " + pet.nome);
    }


    static void mostrarFicha(Pet pet, String titulo) {
        System.out.println(titulo);
        System.out.println("Nome: " + pet.nome);
        System.out.println("Espécie: " + pet.especie);
        System.out.printf("Peso: %.1f kg\n", pet.peso);
        System.out.println("Porte: " + pet.porte);
    }
}
