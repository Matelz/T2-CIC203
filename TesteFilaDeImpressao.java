/*
 * Por: Mateus Silva Pereira (24.00511-8)
 * Data: 31/05/2025
 */

import java.util.Date;
import java.util.Scanner;

public class TesteFilaDeImpressao {
  private static Arquivo criarArquivo(Scanner scanner) {
    System.out.println("Digite o nome do arquivo:");
    String name = scanner.nextLine();
    System.out.println("Digite o nome do usuário:");
    String user = scanner.nextLine();
    
    if (name.isEmpty() || user.isEmpty()) {
      System.out.println("Nome do arquivo e usuário nao podem ser vazios.");
      return null;
    }
    
    return new Arquivo(name, user);
  }

  private static void imprimirArquivo(Fila fila) {
    if (fila.filaVazia()) {
      System.out.println("A fila está vazia. Nenhum arquivo para imprimir.");
      return;
    }
    
    Arquivo arquivo = fila.desenfileira();
    arquivo.setPrinted(true);
    Long tempoEspera = new Date().getTime() - arquivo.getTimestamp().getTime();

    System.out.println("Imprimindo arquivo: " + arquivo.toString());
    System.out.println("Arquivo impresso com sucesso.");
    System.out.println("Tempo de espera na fila: " + (tempoEspera / 1000) + " segundos");
  }

  private static void buscarArquivo(Fila fila, String nomeArquivo) {
    if (fila.filaVazia()) {
      System.out.println("A fila está vazia. Nenhum arquivo para buscar.");
      return;
    }

    boolean encontrado = false;
    for (int i = 0; i < fila.ocupacao; i++) {
      Arquivo arquivo = fila.dados[(fila.primeiro + i) % fila.dados.length];
      if (arquivo.getName().equalsIgnoreCase(nomeArquivo)) {
        System.out.println("Arquivo encontrado: " + arquivo.toString() + 
                           (arquivo.isPrinted() ? " (já impresso)" : " (nao impresso)") + " posiçao na fila: " + (i + 1) + "º");
        encontrado = true;
        break;
      }
    }

    if (!encontrado) {
      System.out.println("Arquivo nao encontrado na fila.");
    }
  }

  // Overload do método buscarArquivo para a fila de reimpressao
  private static void buscarArquivo(Pilha pilhaReimpressao, String nomeArquivo) {
    if (pilhaReimpressao.pilhaVazia()) {
      System.out.println("A fila de reimpressao está vazia. Nenhum arquivo para buscar.");
      return;
    }

    boolean encontrado = false;
    Pilha aux = new Pilha();
    
    while (!pilhaReimpressao.pilhaVazia()) {
      Arquivo arquivo = pilhaReimpressao.pop();
      if (arquivo.getName().equalsIgnoreCase(nomeArquivo)) {
        System.out.println("Arquivo encontrado na fila de reimpressao: " + arquivo.toString() + 
                           (arquivo.isPrinted() ? " (já impresso)" : " (nao impresso)"));
        encontrado = true;
      }
      aux.push(arquivo);
    }

    // Restaura a pilha original
    while (!aux.pilhaVazia()) {
      pilhaReimpressao.push(aux.pop());
    }

    if (!encontrado) {
      System.out.println("Arquivo nao encontrado na fila de reimpressao.");
    }
  }

  public static void main(String[] args) {
    boolean exited = false;
    int opt = -1;
    int maxSize = 1; // Tamanho máximo da fila e pilha de reimpressao

    Scanner scanner = new Scanner(System.in);
    Fila fila = new Fila(maxSize);
    Pilha pilhaReimpressao = new Pilha(maxSize);

    while (!exited) {
      System.out.println("Selecione uma opçao:");
      System.out.println("1 - Adicionar arquivo à fila");
      System.out.println("2 - Imprimir próximo arquivo da fila");
      System.out.println("3 - Exibir arquivos na fila");
      System.out.println("4 - Buscar arquivo na fila");
      System.out.println("5 - Adicionar documento à pilha de reimpressao (emergencial)");
      System.out.println("6 - Imprimir próximo arquivo da pilha de reimpressao (emergencial)");
      System.out.println("7 - Exibir arquivos na pilha de reimpressao (emergencial)");
      System.out.println("8 - Buscar arquivo na pilha de reimpressao (emergencial)");
      System.out.println("9 - Sair");

      try {
        opt = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Opçao inválida. Tente novamente.");
        continue;
      }

      switch (opt) {
        case 1:
          Arquivo arquivo = criarArquivo(scanner);
          if (arquivo != null) {
            try {
              fila.enfileira(arquivo);
            } catch (RuntimeException e) {
              if (fila.ocupacao == maxSize) {
                System.out.println("Fila cheia. Nao é possível adicionar mais arquivos.");
              } else {
                System.out.println("Erro ao adicionar arquivo à fila: " + e.getMessage());
              }

              continue;
            }
            System.out.println("Arquivo adicionado à fila: " + arquivo.toString());
          }
          break;
        case 2:
          imprimirArquivo(fila);
          break;
        case 3:
          System.out.println("Arquivos na fila:");
          System.out.println(fila.toString());
          break;
        case 4:
          System.out.println("Digite o nome do arquivo a buscar:");
          String nomeArquivo = scanner.nextLine();
          buscarArquivo(fila, nomeArquivo);
          break;
        case 5:
          Arquivo reimpressaoArquivo = criarArquivo(scanner);
          if (reimpressaoArquivo != null) {
            try {
              pilhaReimpressao.push(reimpressaoArquivo);
            } catch (RuntimeException e) {
              System.out.println("Erro ao adicionar arquivo à fila: " + e.getMessage());

              continue;
            }
            System.out.println("Arquivo adicionado à pilha de reimpressao: " + reimpressaoArquivo.toString());
          }
          break;
        case 6:
          if (pilhaReimpressao.pilhaVazia()) {
            System.out.println("A pilha de reimpressao está vazia. Nenhum arquivo para imprimir.");
          } else {
            Arquivo reimpressaoArquivoImprimir = pilhaReimpressao.pop();
            reimpressaoArquivoImprimir.setPrinted(true);
            Long tempoEspera = new Date().getTime() - reimpressaoArquivoImprimir.getTimestamp().getTime();
            System.out.println("Imprimindo arquivo da pilha de reimpressao: " + reimpressaoArquivoImprimir.toString());
            System.out.println("Arquivo impresso com sucesso.");
            System.out.println("Tempo de espera na pilha de reimpressao: " + (tempoEspera / 1000) + " segundos");
          }
          break;
        case 7:
          System.out.println("Arquivos na pilha de reimpressao:");
          System.out.println(pilhaReimpressao.toString());
          break;
        case 8:
          System.out.println("Digite o nome do arquivo a buscar na pilha de reimpressao:");
          String nomeArquivoReimpressao = scanner.nextLine();
          buscarArquivo(pilhaReimpressao, nomeArquivoReimpressao);
          break;
        case 9:
          System.out.println("Saindo do programa.");
          exited = true;
          break;
        default:
          System.out.println("Opçao inválida. Tente novamente.");
          break;
      }
    }
  }
}
