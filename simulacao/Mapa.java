package simulacao;

import java.util.ArrayList;

import simulacao.grafo.Grafo;
import simulacao.grafo.Vertice;

/**
 * Representa um mapa com todos os itens que participam da simulacao usando de singleton
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {
    
    private static Mapa mapa;
    
    private Item[][] itens;
    private int largura;
    private int altura;
    
    private static final int LARGURA_PADRAO = 34;
    private static final int ALTURA_PADRAO = 34;
    
    private static int[][] ruas;

    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulacao.
     */
    private Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Item[altura][largura];
        ruas = new int[altura][largura];
        gerarRuas();
        Grafo g = new Grafo(this);
        g.dijkstra(g.getV(0));
    }
    /**
     * Cria mapa com tamanho padrao.
     */
    private Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    /**
     * Metodo que retorna a referência do mapa.
     * 
     * Instancia o mapa, caso ainda nao esteja instanciado.
     * @return Mapa
     */
    public static Mapa getMapa(){
        if(mapa == null){
            mapa = new Mapa();
        }
        return mapa;
    }

    /**
     * Metodo responsavel por adicior um item ao mapa;
     * @param v
     */
    public void adicionarItem(Item v){
        itens[v.getLocalizacao().getX()][v.getLocalizacao().getY()] = v;
    }
    
    /**
     * Metodo responsavel por remover um item ao mapa;
     * @param v
     */
    public void removerItem(Item v){
        itens[v.getLocalizacao().getX()][v.getLocalizacao().getY()] = null;
    }
    
    /**
     * Metodo responsavel por atualizar um item no mapa;
     * @param v
     */
    public void atualizarMapa(Item v){
        removerItem(v);
        adicionarItem(v);
    }
    
    /**
     * Metodo que retorna um item.
     * 
     * @param x - coordenada X.
     * @param y - coordenada Y.
     * @return Item
     */
    public Item getItem(int x, int y){
        return itens[x][y];
    }

    /**
     * Metodo que retorna a largura maxima do mapa.
     * 
     * @return int
     */
    public int getLargura() {
        return largura;
    }

    /**
     * Metodo que retorna a altura maxima do mapa.
     * 
     * @return int
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Metodo responsavel por gerar esquema de ruas e ambientes.
     * 
     * Define como rua(1) coordenadas em que pelo um valor de eixo eh divisível por 3. Caso contrario eh um ambiente(0).
     */
    private void gerarRuas(){
        for(int i = 0; i < altura; i++){
            for(int j = 0; j < largura; j++){
                if( i % 3 == 0 || j % 3 == 0){
                    ruas[i][j] = 1;
                }
            }
        }
    }

    /**
     * Metodo que retorna se eh uma rua ou um ambiente.
     * 
     * @param x - coordenada X.
     * @param y - coordenada Y.
     * @return int - 1(rua) ou  0(ambiente).
     */
    public int getRua(int x, int y){
        return ruas[x][y];
    }

    /**
     *  Metodo que retorna a matriz com o esquema de ruas.
     * 
     * @return int[][]
     */
    public static int[][] getRuas() {
        return ruas;
    }

}
