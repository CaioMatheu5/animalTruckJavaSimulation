package simulacao.grafo;

import java.util.ArrayList;

import simulacao.Mapa;

public class Grafo {
    private ArrayList<Vertice> V;
    private ArrayList<Aresta> E;

    public Grafo(Mapa m){
        int h = m.getAltura();
        int w = m.getLargura();

        V = new ArrayList<>();
        E = new ArrayList<>();

        inicializaVertice(h, w);
        inicializaAresta(h,w);

        System.out.println(E.size());
        
    }

    private void inicializaVertice(int h, int w){

        int[][] ruas = Mapa.getRuas();

        for (int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(ruas[i][j] == 1){
                    Vertice v = new Vertice(i, j);
                    V.add(v);
                }
            }
        }
    }

    private void inicializaAresta(int h,int w) {
        for (Vertice v : V) {

            int i = v.getI();
            int j = v.getJ();

            if (i % 3 == 0 && j > 0) {
                if (i % 2 == 0) {
                    E.add(new Aresta(V.get(V.indexOf(v) - 1), v, 1));
                } else {
                    E.add(new Aresta(v, V.get(V.indexOf(v) - 1), 1));
                }
            }

            if (j % 3 == 0 && i < h-1) {
                if (j % 2 == 0) {
                    if (i %3 == 0)
                        E.add(new Aresta(v,V.get(V.indexOf(v) + w - ((j/3)*2) ), 1));
                    else if (i % 2 == 0){
                        E.add(new Aresta(v,V.get(V.indexOf(v) + 12 + ((j/3)*2) ), 1));
                    }
                    else {
                        E.add(new Aresta(v,V.get(V.indexOf(v) + 12 ), 1));
                    }
                }else {
                    if (i %3 == 0)
                        E.add(new Aresta(V.get(V.indexOf(v) + w - ((j/3)*2) ),v, 1));
                    else if (i % 2 == 0){
                        E.add(new Aresta(V.get(V.indexOf(v) + 12 + ((j/3)*2) ),v, 1));
                    }
                    else {
                        E.add(new Aresta(V.get(V.indexOf(v) + 12 ), v,1));
                    }
                }
            }
        }
    }
    
}