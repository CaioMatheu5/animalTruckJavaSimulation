package simulacao;

import javax.swing.ImageIcon;

public class Obra extends Item{
    
    public Obra(Localizacao localizacao){
        super(localizacao);
        super.setImagem(new ImageIcon(getClass().getResource("Imagens/Obstaculo.png")).getImage());
    }

}
