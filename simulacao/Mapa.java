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
    
    /**
     * Método que retorna a referência do mapa.
     * 
     * Instância o mapa, caso ainda não esteja instanciado.
     * @return Mapa
     */
    public static Mapa getMapa(){
        if(mapa == null){
            mapa = new Mapa();
        }
        return mapa;
    }

    /**
     * Método responsável por adicior um item ao mapa;
     * @param v
     */
    public void adicionarItem(Item v){
        itens[v.getLocalizacao().getX()][v.getLocalizacao().getY()] = v;
    }
    
    /**
     * Método responsável por remover um item ao mapa;
     * @param v
     */
    public void removerItem(Item v){
        itens[v.getLocalizacao().getX()][v.getLocalizacao().getY()] = null;
    }
    
    /**
     * Método responsável por atualizar um item no mapa;
     * @param v
     */
    public void atualizarMapa(Item v){
        removerItem(v);
        adicionarItem(v);
    }
    
    /**
     * Método que retorna um item.
     * 
     * @param x - coordenada X.
     * @param y - coordenada Y.
     * @return Item
     */
    public Item getItem(int x, int y){
        return itens[x][y];
    }

    /**
     * Método que retorna a largura máxima do mapa.
     * 
     * @return int
     */
    public int getLargura() {
        return largura;
    }

    /**
     * Método que retorna a altura máxima do mapa.
     * 
     * @return int
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Método responsável por gerar esquema de ruas e ambientes.
     * 
     * Define como rua(1) coordenadas em que pelo um valor de eixo é divisível por 3. Caso contrario é um ambiente(0).
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
     * Método que retorna se é uma rua ou um ambiente.
     * 
     * @param x - coordenada X.
     * @param y - coordenada Y.
     * @return int - 1(rua) ou  0(ambiente).
     */
    public int getRua(int x, int y){
        return ruas[x][y];
    }

    /**
     *  Método que retorna a matriz com o esquema de ruas.
     * 
     * @return int[][]
     */
    public static int[][] getRuas() {
        return ruas;
    }

}
