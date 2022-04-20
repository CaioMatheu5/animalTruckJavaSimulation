package simulacao;

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
}
