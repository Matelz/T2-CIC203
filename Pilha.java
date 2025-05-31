public class Pilha {
    private No topo;
    private int tamanho;
    private int ocupacao = 0;

    public Pilha() {
        topo = null;
    }

    public Pilha(int tamanho) {
        this.tamanho = tamanho;
        this.topo = null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getOcupacao() {
        return ocupacao;
    }

    public boolean pilhaVazia () {
        return topo == null;
    }

    public void push (Arquivo info) {
        if (ocupacao == tamanho) {
            throw new RuntimeException("falha no empilhamento: pilha cheia");
        }

        No novo = new No(info);
        if (!pilhaVazia()) {
            novo.setProximo(topo);
        }
        ocupacao++;
        topo = novo;
    }

    public Arquivo pop() {
        if (pilhaVazia()) throw new RuntimeException("falha no desempilhamento");
        Arquivo temp = topo.getInfo();
        topo = topo.getProximo();
        ocupacao--;
        return temp;
    }

    public Arquivo peek () {
        if (pilhaVazia()) throw new RuntimeException("falha na consulta");
        return topo.getInfo();
    }

    @Override
    public String toString() {
        if (pilhaVazia()) return "pilha vazia\n";
        String s = "-------\n";
        No runner = topo;
        while (runner != null) {
            s += runner + "\n";
            runner = runner.getProximo(); 
        }
        return s + "\n";
    }

    public void invertePilha() {
        Pilha aux = new Pilha();
        while (!pilhaVazia()) {
            aux.push(this.pop());
        }
        this.topo = aux.topo;
    }
}

class No {
    private Arquivo info;
    private No proximo;
    public No(Arquivo info) {
        this.info = info;
    }
    public Arquivo getInfo() {
        return info;
    }
    public No getProximo() {
        return proximo;
    }
    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
    @Override
    public String toString () {
        return info.toString();
    }
}