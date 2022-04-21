package simulacao;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Representa os semáforos da simulacao.
 * @author Caio M. L. Silva, Erick C. Silva, Felipe O. Fernandes e Jonathas L. Sousa
 */
public class Semaforo extends Item implements Atualizavel{
    
    private String estado;

    /**
     * Cria um semáfaro a partir de uma localizacao, com estado inicialmente "verde".
     * 
     * @param localizacao
     */
    public Semaforo(Localizacao localizacao){
        super(localizacao);
        
        setEstado("verde");
    }

    /**
     * Método responsável por definir como o semáforo deve agir na janela de simulação.
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
     * Método que atribui um estado ao semáforo e a imagem correspondente, no caso "verde" ou "vermelho".
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
     * Método responsável por gerar uma localização aleatória.
     * 
     * @param rand - objeto Random para gerar um número aleatório.
     * @param altura - alura máxima do mapa.
     * @param largura - largura máxima do mapa.
     * @return Localizacao - uma localização aleatória válida, no caso, uma localização que não seja rua.
     */
    public static Localizacao gerarLocalizaoAleatoria(Random rand, int altura,int  largura){
        Localizacao localizacao;
        do{
            localizacao = new Localizacao(rand.nextInt(largura),rand.nextInt(altura));
        }while(!validarLocalizacao(localizacao));

        return localizacao;
    }
    
    /**
     * Método responsável por validar uma localização.
     * 
     * @param localizacao
     * @return boolean - true se e a localização não é ocupada e não é uma rua, caso contrário retorna false. 
     */
    protected  static boolean validarLocalizacao(Localizacao localizacao){
        Mapa mapa = Mapa.getMapa();

        return mapa.getItem(localizacao.getX(), localizacao.getY()) == null && mapa.getRua(localizacao.getX(), localizacao.getY()) == 0;
    
    }
}
