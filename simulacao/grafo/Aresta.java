package simulacao.grafo;

public class Aresta {
    private Vertice v1,v2;
    private int peso;

    public Aresta(Vertice v1, Vertice v2, int p){
        this.v1 = v1;
        this.v2 = v2;
        this.peso = p;
    }

    public Vertice getV1() {
        return v1;
    }
    public Vertice getV2() {
        return v2;
    }
    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return v1 + "->" + v2;
    }
}
