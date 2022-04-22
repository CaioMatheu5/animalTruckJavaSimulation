package simulacao.grafo;

public class Vertice implements Comparable<Vertice>{
    private static final int inf = 9999999;

    private int i;
    private int j;
    private int dist;
    private boolean visitado;
    private Vertice pai;

    public Vertice(int i, int j){
        this.i = i;
        this.j = j;
        dist = inf;
        visitado = false;
        pai = null;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
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
    public boolean getVisitado(){
        return this.visitado;
    }
    public void setPai(Vertice pai) {
        this.pai = pai;
    }
    public Vertice getPai() {
        return pai;
    }

    @Override
    public String toString() {
        return "("+getI()+","+getJ()+")";
    }

    @Override
    public int compareTo(Vertice o) {
        if (this.getDist() > o.getDist())
            return 1;
        else if (this.getDist() < o.getDist())
            return -1;
        else 
            return 0;
    }
}
