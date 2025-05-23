import java.util.Scanner;

public class TesteFilaDeImpressao {
  private static Arquivo criarArquivo(String name, String user) {
    return new Arquivo(name, user);
  }

  public static void main(String[] args) {
    boolean exited = false;
    int opt = -1;
    int capacidade = 5;
    Scanner scanner = new Scanner(System.in);
    Fila filaDeImpressao = new Fila(capacidade);

    while (!exited) {
      System.out.println(
          "===== Gráfica Rápida =====\nSelecione uma opção:\n1-\tInserir novo arquivo\n2-\tVer lista de impressão\n3-\tVerificar arquivo\n");

      opt = scanner.nextInt();
      scanner.nextLine();

      switch (opt) {
        case 1:
          String nomeTemp = null;
          System.out.print("==== Inserir arquivo ====\nDigite o nome do arquivo:\n(pressione enter para enviar)\n");
          nomeTemp = scanner.nextLine();

          String userTemp = null;
          System.out.println("Digite o seu nome:\n(pressione enter para enviar)\n");
          userTemp = scanner.nextLine();

          try {
            filaDeImpressao.enfileira(criarArquivo(nomeTemp, userTemp));
            System.out.println("Seu arquivo foi inserido com sucesso! " + filaDeImpressao.toString());
          } catch (Exception e) {
            System.out.println("A fila está cheia, por favor, tente novamente mais tarde");
            opt = -1;
          }
          break;
        case 2:
          System.out.printf("==== Status da fila (%d/%d) ====\n" + filaDeImpressao.toString() + "\n",
              filaDeImpressao.ocupacao,
              capacidade);
          break;
      }
    }

    scanner.close();
  }
}
