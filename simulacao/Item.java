package simulacao;

import java.awt.Image;

public abstract class Item {
    private Localizacao localizacao;
    private Image imagem;

    public Item(Localizacao localizacao){
        this.localizacao = localizacao;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }


}
