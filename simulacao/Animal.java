package simulacao;

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

}
