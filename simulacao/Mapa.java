package simulacao;

import simulacao.grafo.Grafo;

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
     * @param altura: altura da área de simulação.
     */
    private Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Item[altura][largura];
        ruas = new int[altura][largura];
        gerarRuas();
        new Grafo(this);
    }
    /**
     * Cria mapa com tamanho padrao.
     */
    private Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }

    public static Mapa getMapa(){
        if(mapa == null){
            mapa = new Mapa();
        }
        return mapa;
    }

    public void adicionarItem(Item v){
        itens[v.getLocalizacao().getX()][v.getLocalizacao().getY()] = v;
    }
    
    public void removerItem(Item v){
        itens[v.getLocalizacao().getX()][v.getLocalizacao().getY()] = null;
    }
    
    public void atualizarMapa(Item v){
        removerItem(v);
        adicionarItem(v);
    }
    
    public Item getItem(int x, int y){
        return itens[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    private void gerarRuas(){
        for(int i = 0; i < altura; i++){
            for(int j = 0; j < largura; j++){
                if( i % 3 == 0 || j % 3 == 0){
                    ruas[i][j] = 1;
                }
            }
        }
    }

    public int getRua(int x, int y){
        return ruas[x][y];
    }

    public static int[][] getRuas() {
        return ruas;
    }

}
