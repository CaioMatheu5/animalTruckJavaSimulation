package simulacao;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Veiculo extends Item implements Atualizavel{

    private Localizacao localizacaoDestino;

    /**
     * Cria  um veiculo a partir de uma localizacao.
     * @param localizacao
     */
    public Veiculo(Localizacao localizacao) {
        super(localizacao);
        super.setImagem(new ImageIcon(getClass().getResource("Imagens/CaminhaoBaixoCima.png")).getImage());
        localizacaoDestino = null;
    }

    /**
     * Metodo que retorna uma localizacao de destino.
     * @return Localizacao
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    /**
     * Metodo que atribui uma localizacao de destino.
     * @param localizacaoDestino
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }
    
    /**
     * Metodo responsavel por definir como o veiculo deve agir na janela de simulacao.
     * Caso o destino nao seja nulo, eh atribuido uma nova localizacao
     * para o veiculo e eh atualizada a imagem do mesmo. 
     */
    @Override
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = super.getLocalizacao().proximaLocalizacao(localizacaoDestino);
            atualizarImagem(proximaLocalizacao);
            super.setLocalizacao(proximaLocalizacao);
        }
    }
    
    /**
     * Metodo responsavel por definir como a imagem do veiculo deve ser atualizada.
     * Tendo como parametro a proximaLocalizacao uma nova imagem eh atribuida.
     * @param proximaLocalizacao
     */
    private void atualizarImagem(Localizacao proximaLocalizacao){
        Localizacao localizacaoAtual = super.getLocalizacao();
        String caminhoImagem;
        if(localizacaoAtual.getX() < proximaLocalizacao.getX()){
            caminhoImagem = "Imagens/CaminhaoDirEsq.png";
        }else if(localizacaoAtual.getX() > proximaLocalizacao.getX()){
            caminhoImagem = "Imagens/CaminhaoEsqDir.png";
        }else if(localizacaoAtual.getY() < proximaLocalizacao.getY()){
            caminhoImagem = "Imagens/CaminhaoCimaBaixo.png";
        }else{
            caminhoImagem = "Imagens/CaminhaoBaixoCima.png";
        }

        super.setImagem(new ImageIcon(getClass().getResource(caminhoImagem)).getImage());
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
