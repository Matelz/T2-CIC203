public class Fila {
  Arquivo[] dados;
  int primeiro, ultimo, ocupacao;

  public Fila(int capacidade) {
    dados = new Arquivo[capacidade];
    // por clareza
    primeiro = 0;
    ultimo = 0;
    ocupacao = 0;
  }

  public Fila() {
    this(10);
  }

  public boolean filaVazia() {
    return ocupacao == 0;
  }

  public boolean filaCheia() {
    return ocupacao == dados.length;
  }

  private int proxima(int pos) {
    return (pos + 1) % dados.length;
  }

  public void enfileira(Arquivo e) {
    if (filaCheia())
      throw new RuntimeException("falha no enfileiramento");
    dados[ultimo] = e;
    ultimo = proxima(ultimo);
    ocupacao++;
  }

  public Arquivo desenfileira() {
    if (filaVazia())
      throw new RuntimeException("falha no desenfileiramento");
    Arquivo temp = dados[primeiro];
    primeiro = proxima(primeiro);
    ocupacao--;
    return temp;
  }

  @Override
  public String toString() {
    if (filaVazia())
      return "fila vazia";
    String s = "";
    for (int i = primeiro, cont = 0; cont < ocupacao; cont++) {
      s += dados[i] + "\n";
      i = proxima(i);
    }
    return s;
  }

  public String stringVetor() {
    String s = "";
    int i;
    if (filaVazia())
      for (i = 0; i < dados.length; i++)
        s += "_ ";
    else if (filaCheia())
      for (i = 0; i < dados.length; i++)
        s += dados[i] + " ";
    else if (primeiro < ultimo) {
      for (i = 0; i < primeiro; i++)
        s += "_ ";
      for (i = primeiro; i < ultimo; i++)
        s += dados[i] + " ";
      for (i = ultimo; i < dados.length; i++)
        s += "_ ";
    } else {
      for (i = 0; i < ultimo; i++)
        s += dados[i] + " ";
      for (i = ultimo; i < primeiro; i++)
        s += "_ ";
      for (i = primeiro; i < dados.length; i++)
        s += dados[i] + " ";
    }
    return s;
  }
}
