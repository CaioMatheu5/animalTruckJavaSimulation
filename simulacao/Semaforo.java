package simulacao;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Representa os semaforos da simulacao.
 * @author Caio M. L. Silva, Erick C. Silva, Felipe O. Fernandes e Jonathas L. Sousa
 */
public class Semaforo extends Item implements Atualizavel{
    
    private String estado;

    /**
     * Cria um semafaro a partir de uma localizacao, com estado inicialmente "verde".
     * 
     * @param localizacao
     */
    public Semaforo(Localizacao localizacao){
        super(localizacao);
        
        setEstado("verde");
    }

    /**
     * Metodo responsavel por definir como o semafaro deve agir na janela de simulacao.
     */
    @Override
    public void executarAcao(){
        if(estado.equals("verde")){
            setEstado("vermelho");
        }else{
            setEstado("verde");
        }
    }

    /**
     * Metodo que atribui um estado ao semafaro e a imagem correspondente, no caso "verde" ou "vermelho".
     * 
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
        if( estado.equals("verde")){ 
            super.setImagem(new ImageIcon(getClass().getResource("Imagens/SemaforoVerde.png")).getImage());
        }else{
            super.setImagem(new ImageIcon(getClass().getResource("Imagens/SemaforoVermelho.png")).getImage());
        }
    }

    /**
     * Metodo responsavel por gerar uma localizacao aleatoria.
     * 
     * @param rand - objeto Random para gerar um numero aleatorio.
     * @param altura - alura maxima do mapa.
     * @param largura - largura maxima do mapa.
     * @return Localizacao - uma localizacao aleatoria valida, no caso, uma localizacao que nao seja rua.
     */
    public static Localizacao gerarLocalizaoAleatoria(Random rand, int altura,int  largura){
        Localizacao localizacao;
        do{
            localizacao = new Localizacao(rand.nextInt(largura),rand.nextInt(altura));
        }while(!validarLocalizacao(localizacao));

        return localizacao;
    }
    
    /**
     * Metodo responsavel por validar uma localizacao.
     * 
     * @param localizacao
     * @return boolean - true se e a localizacao nao eh ocupada e nao eh uma rua, caso contrario retorna false. 
     */
    protected  static boolean validarLocalizacao(Localizacao localizacao){
        Mapa mapa = Mapa.getMapa();

        return mapa.getItem(localizacao.getX(), localizacao.getY()) == null && mapa.getRua(localizacao.getX(), localizacao.getY()) == 0;
    
    }
}
