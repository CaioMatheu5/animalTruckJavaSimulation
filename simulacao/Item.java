package simulacao;

import java.awt.Image;


/**
 * Classe abstrata responsável por definir a base dos itens da simulação.
 * 
 * @author Caio M. L. Silva, Erick C. Silva, Felipe O. Fernandes e Jonathas L. Sousa
 */
public abstract class Item {
    private Localizacao localizacao;
    private Image imagem;

    /**
     * Cria uma item a partir de uma localizacao.
     * @param localizacao
     */
    public Item(Localizacao localizacao){
        this.localizacao = localizacao;
    }

    /**
     * Método que retorna a localização do item.
     * @return Localizacao
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    /**
     * Método que retorna a imagem do item.
     * @return Image
     */
    public Image getImagem() {
        return imagem;
    }
    
     /**
     * Método que atribui uma localizacao de destino.
     * @param localizacaoDestino
     */
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

 /**
     * Método que atribui uma imagem.
     * @param imagem
     */
    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

}
