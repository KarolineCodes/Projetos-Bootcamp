# Projeto Petshop

Sistema de petshop em Java, feito em cima da lista de atividades (questões 1 a 16) e organizado seguindo os princípios **S.O.L.I.D**.

## Como rodar

```bash
javac -encoding UTF-8 -d out src/petshop/*.java
java -cp out petshop.Petshop
```

No Eclipse/IntelliJ: abra a pasta `src`, o package é `petshop` e a classe com o `main` é `Petshop`.

> Se der erro ao digitar o peso com ponto (8.5), digite com vírgula (8,5). É o computador configurado em português.

## Menu

```
=== PETSHOP ===
1 - Cadastrar pet
2 - Listar pets
3 - Buscar pet pelo nome
4 - Espécies cadastradas
5 - Entrar na fila de banho
6 - Atender próximo da fila
7 - Executar serviços
8 - Ver ficha do pet
0 - Sair
```

## Classes

| Arquivo | O que faz |
|---|---|
| `Petshop` | Só conversa com o usuário: mostra o menu, lê o teclado e mostra os resultados. Tem o `main`. |
| `Pet` | Dados do pet + o que é do próprio pet (`definirPorte()`, `emitirSom()`). |
| `Cachorro`, `Gato` | Filhas de `Pet`, cada uma com o seu som (`@Override`). |
| `FabricaPet` | Monta o pet certo (Cachorro, Gato ou Pet) de acordo com a espécie. |
| `RepositorioPets` | Interface: o contrato de "guardar e encontrar pets". |
| `RepositorioPetsArray` | Implementação com array de 5 posições. |
| `FilaBanho` | A fila de banho (quem entra primeiro é atendido primeiro). |
| `Servico` | Interface dos serviços (`executar`). |
| `Banho`, `Tosa`, `Consulta` | Os serviços, cada um implementando `Servico`. |

## Onde está cada princípio SOLID

**S — Responsabilidade Única.** Cada classe cuida de um assunto. O `Petshop` não guarda pets (isso é o `RepositorioPetsArray`), não controla fila (`FilaBanho`) e não decide qual classe criar (`FabricaPet`). O `Pet` não lê teclado. Se mudar o jeito de guardar os pets, o menu não é afetado.

**O — Aberto-Fechado.** Pra criar um serviço novo (ex: Vacina), é só criar `Vacina implements Servico` e adicionar uma linha no `main`. O laço que executa os serviços não muda. O mesmo vale pro som: o `listarPets()` chama `pet.emitirSom()` sem nenhum `if` perguntando a espécie.

**L — Substituição de Liskov.** `Cachorro` e `Gato` podem ser usados em qualquer lugar que espera um `Pet`. Eles sobrescrevem o `emitirSom()` cumprindo o que a mãe promete (emitir um som), sem método vazio e sem lançar erro. Por isso o array de `Pet` aceita os três tipos.

**I — Segregação de Interface.** As interfaces são pequenas e focadas: `Servico` tem um método só. `RepositorioPets` tem só o que o `Petshop` usa.

**D — Inversão de Dependência.** O `Petshop` não dá `new` nas peças que usa. Ele recebe o repositório, a fila e os serviços prontos pelo construtor e conhece só os contratos (`RepositorioPets`, `Servico`). Quem escolhe as peças concretas é o `main`. Pra trocar o array por um `ArrayList<Pet>` (desafio 8), basta criar um `RepositorioPetsLista implements RepositorioPets` e trocar uma linha no `main`.

> Observação: o `switch` do menu (questão 7) e o `if/else` da espécie (questão 14) foram mantidos porque a lista pede essas estruturas. Pra isolar o impacto, o `if/else` da espécie ficou concentrado na `FabricaPet`, e cada `case` do menu só chama um método.

## Questões

| Questão | Onde |
|---|---|
| 1, 2, 3 | `Petshop.cadastrarPet()` e `mostrarFicha(pet, titulo)` |
| 4, 5 | `Pet` (`definirPorte()`) |
| 6, 7 | `RepositorioPetsArray` (array + `total++`) e `Petshop.iniciar()` |
| 8, 15 | `Petshop.listarPets()` |
| 9 | `RepositorioPetsArray.buscarPorNome()` e `Petshop.buscarPet()` |
| 10 | `try / catch (InputMismatchException)` em `Petshop.iniciar()` |
| 11 | `RepositorioPetsArray.especiesCadastradas()` (HashSet) |
| 12 | `FilaBanho` (ArrayDeque) |
| 13 | `Servico`, `Banho`, `Tosa`, `Consulta` |
| 14 | `Cachorro`, `Gato`, `FabricaPet` |
| 16 | `Petshop.mostrarFicha(...)` (sobrecarga) |

Extra: o cadastro avisa `Cadastro cheio!` quando o array lota, pra não quebrar no 6º pet.
