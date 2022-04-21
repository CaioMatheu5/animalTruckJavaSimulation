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
     * Cria  um veículo a partir de uma localizacao.
     * @param localizacao
     */
    public Veiculo(Localizacao localizacao) {
        super(localizacao);
        super.setImagem(new ImageIcon(getClass().getResource("Imagens/CaminhaoBaixoCima.png")).getImage());
        localizacaoDestino = null;
    }

    /**
     * Método que retorna uma localizacao de destino.
     * @return Localizacao
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    /**
     * Método que atribui uma localizacao de destino.
     * @param localizacaoDestino
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }
    
    /**
     * Método responsável por definir como o veículo deve agir na janela de simulação.
     * Caso o destino não seja nulo, é atribuido uma nova localização
     * para o veículo e é atualizada a imagem do mesmo. 
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
     * Método responsável por definir como a imagem do veículo deve ser atualizada.
     * Tendo como parâmetro a proximaLocalizacao uma nova imagem é atribuída.
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
     * Método responsável por gerar uma localização aleatória.
     * 
     * @param rand - objeto Random para gerar um número aleatório.
     * @param altura - alura máxima do mapa.
     * @param largura - largura máxima do mapa.
     * @return Localizacao - uma localização aleatória válida, no caso, uma localização de rua.
     */
    public static Localizacao gerarLocalizaoAleatoria(Random rand, int altura,int  largura){
        Localizacao localizacao;
        do{
            localizacao = new Localizacao(rand.nextInt(largura),rand.nextInt(altura));
        }while(!validarLocalizacao(localizacao));

        return localizacao;
    }

    /**
     * Método responsável por validar uma localização.
     * 
     * @param localizacao
     * @return boolean - true se e a localização não é ocupada e é uma rua, caso contrário retorna false. 
     */
    protected  static boolean validarLocalizacao(Localizacao localizacao){
        Mapa mapa = Mapa.getMapa();
        return mapa.getItem(localizacao.getX(), localizacao.getY()) == null && mapa.getRua(localizacao.getX(), localizacao.getY()) == 1;
    
    }
}
