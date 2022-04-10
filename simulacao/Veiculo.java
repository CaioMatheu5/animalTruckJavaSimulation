package simulacao;

import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Veiculo extends Item implements Atualizavel{

    private Localizacao localizacaoDestino;

    public Veiculo(Localizacao localizacao) {
        super(localizacao);
        super.setImagem(new ImageIcon(getClass().getResource("Imagens/CaminhaoBaixoCima.png")).getImage());
        localizacaoDestino = null;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
     
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }
    
    @Override
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = super.getLocalizacao().proximaLocalizacao(localizacaoDestino);
            atualizarImagem(proximaLocalizacao);
            super.setLocalizacao(proximaLocalizacao);
        }
    }
    
    private void atualizarImagem(Localizacao proximaLocalizacao){
        Localizacao localizacaoAtual = super.getLocalizacao();
        String caminhoImagem ;
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
}
