package simulacao;

import java.util.Random;

import javax.swing.ImageIcon;

public class Semaforo extends Item implements Atualizavel{
    
    private String estado;

    public Semaforo(Localizacao localizacao){
        super(localizacao);
        
        setEstado("verde");
    }

    @Override
    public void executarAcao(){
        if(estado.equals("verde")){
            setEstado("vermelho");
        }else{
            setEstado("verde");
        }
    }

    public void setEstado(String estado) {
        this.estado = estado;
        if( estado.equals("verde")){ 
            super.setImagem(new ImageIcon(getClass().getResource("Imagens/SemaforoVerde.png")).getImage());
        }else{
            super.setImagem(new ImageIcon(getClass().getResource("Imagens/SemaforoVermelho.png")).getImage());
        }
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

        return mapa.getItem(localizacao.getX(), localizacao.getY()) == null && mapa.getRua(localizacao.getX(), localizacao.getY()) == 0;
    
    }
}
