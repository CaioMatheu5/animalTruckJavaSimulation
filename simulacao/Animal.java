package simulacao;

import java.util.Random;

import javax.swing.ImageIcon;

public class Animal extends Item{
    
    private static int quantidade = 0;

    public static int getQuantidade() {
        return quantidade;
    }

    public Animal(Localizacao localizacao){
        super(localizacao);
        super.setImagem(new ImageIcon(getClass().getResource("Imagens/Animal.png")).getImage());
        quantidade += 1;
    }

    public void removerAnimal(){
        quantidade -= 1;
    }

    public static Localizacao gerarLocalizaoAleatoria(Random rand, int altura,int  largura){
        Localizacao localizacao;
        do{
            localizacao = new Localizacao(rand.nextInt(largura),rand.nextInt(altura));
        }while(!validarLocalizacao(localizacao));

        return localizacao;
    }
    
    protected  static boolean validarLocalizacao(Localizacao localizacao){
        Mapa mapa = Mapa.getMapa();

        return mapa.getItem(localizacao.getX(), localizacao.getY()) == null && mapa.getRua(localizacao.getX(), localizacao.getY()) == 1;
    
    }
}
