package simulacao.grafo;

public class Vertice {
    private static final int inf = 9999999;

    private int i;
    private int j;
    private int dist;
    private Vertice pai;

    public Vertice(int i, int j){
        this.i = i;
        this.j = j;
        dist = inf;
        pai = null;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
    public void setPai(Vertice pai) {
        this.pai = pai;
    }
    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    public int getDist() {
        return dist;
    }
    public Vertice getPai() {
        return pai;
    }

    @Override
    public String toString() {
        return "("+getI()+","+getJ()+")";
    }
}
