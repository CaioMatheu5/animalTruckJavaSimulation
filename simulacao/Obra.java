package simulacao;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Representa as obras da simulacao.
 * @author Caio M. L. Silva, Erick C. Silva, Felipe O. Fernandes e Jonathas L. Sousa
 */
public class Obra extends Item{
    
    /**
     * Cria uma obra a partir de uma localizacao.
     * E seta a imagem da mesma.
     * @param localizacao
     */
    public Obra(Localizacao localizacao){
        super(localizacao);
        super.setImagem(new ImageIcon(getClass().getResource("Imagens/Obstaculo.png")).getImage());
    }

    /**
     * Método responsável por gerar uma localização aleatória.
     * 
     * @param rand - objeto Random para gerar um número aleatório.
     * @param altura - alura máxima do mapa.
     * @param largura - largura máxima do mapa.
     * @return Localizacao - uma localização aleatória válida, no caso, uma rua que não seja intersecção.
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
     * @return boolean - true se e a localização não é ocupada e é uma rua que não seja intersecção, caso contrário retorna false. 
     */
    protected static boolean validarLocalizacao(Localizacao localizacao){
        Mapa mapa = Mapa.getMapa();

        return mapa.getItem(localizacao.getX(), localizacao.getY()) == null && mapa.getRua(localizacao.getX(), localizacao.getY()) == 1 && (localizacao.getX() % 3 != 0 || localizacao.getY() % 3 != 0 );
    
    }
}
