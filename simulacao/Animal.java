package simulacao;

import java.util.Random;

import javax.swing.ImageIcon;

/**
* Representa os animais da simulacao.
* @author Caio M. L. Silva, Erick C. Silva, Felipe O. Fernandes e Jonathas L. Sousa
*/
public class Animal extends Item{
    
    private static int quantidade = 0;

    /**
     * Metodo que retorna a quantidade de animais.
     * @return int
     */
    public static int getQuantidade() {
        return quantidade;
    }

    /**
     * Cria  um animal a partir de uma localizacao.
     * 
     * Incrementa a quantidade de animais.
     * @param localizacao
     */
    public Animal(Localizacao localizacao){
        super(localizacao);
        super.setImagem(new ImageIcon(getClass().getResource("Imagens/Animal.png")).getImage());
        quantidade += 1;
    }

    /**
     * Metodo responsavel por decrementar a quatidade de animais.
     */
    public void removerAnimal(){
        quantidade -= 1;
    }

    /**
     * Metodo responsavel por gerar uma localizacao aleatoria.
     * 
     * @param rand - objeto Random para gerar um numero aleatorio.
     * @param altura - alura maxima do mapa.
     * @param largura - largura maxima do mapa.
     * @return Localizacao - uma localizacao aleatoria valida, no caso, uma localizacao de rua.
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
     * @return boolean - true se e a localizacao nao eh ocupada e eh uma rua, caso contrario retorna false. 
     */
    protected  static boolean validarLocalizacao(Localizacao localizacao){
        Mapa mapa = Mapa.getMapa();

        return mapa.getItem(localizacao.getX(), localizacao.getY()) == null && mapa.getRua(localizacao.getX(), localizacao.getY()) == 1;
    
    }
}
