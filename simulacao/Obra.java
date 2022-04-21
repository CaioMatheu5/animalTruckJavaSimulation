package simulacao;

import java.util.Random;

import javax.swing.ImageIcon;

public class Obra extends Item{
    
    public Obra(Localizacao localizacao){
        super(localizacao);
        super.setImagem(new ImageIcon(getClass().getResource("Imagens/Obstaculo.png")).getImage());
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

        return mapa.getItem(localizacao.getX(), localizacao.getY()) == null && mapa.getRua(localizacao.getX(), localizacao.getY()) == 1 && (localizacao.getX() % 3 != 0 || localizacao.getY() % 3 != 0 );
    
    }
}
