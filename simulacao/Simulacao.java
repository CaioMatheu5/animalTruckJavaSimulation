package simulacao;

import java.util.ArrayList;
import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private ArrayList<Atualizavel> atualizaveis;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    /**
     * Cria uma simulacao.
     * Atribui mapa, atualizaveis, gera itens e cria a janela de simulacao.
     */
    public Simulacao() {
        mapa = Mapa.getMapa();
        atualizaveis = new ArrayList<Atualizavel>();
        gerarItens();
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    /**
     * Metodo responsavel por gerar todos os itens aleatoriamente dentro dos limites do mapa.
     */
    private void gerarItens(){
        Random random = new Random(2345678);
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        int quantidadeItens = 10;
        gerarObras(random, altura, largura, quantidadeItens);
        gerarSemaforos(random, altura, largura, quantidadeItens);
        gerarAnimais(random, altura, largura, quantidadeItens);
        gerarVeiculos(random, altura, largura, quantidadeItens);
    }
 
    /**
     * Metodo reponsavel por gerar obras. 
     * 
     * @param random - objeto Random para gerar um numero aleatorio.
     * @param altura - alura maxima do mapa.
     * @param largura - largura maxima do mapa.
     * @param quantidadeItens - quantidade a ser gerada.
     */
    private void gerarObras(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < quantidadeItens;i++){
            Obra obra = new Obra(Obra.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(obra);
        }
    }

    /**
     * Metodo reponsavel por gerar semaforos. 
     * 
     * @param random - objeto Random para gerar um numero aleatorio.
     * @param altura - alura maxima do mapa.
     * @param largura - largura maxima do mapa.
     * @param quantidadeItens - quantidade a ser gerada.
     */
    private void gerarSemaforos(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < quantidadeItens;i++){
            Semaforo semaforo = new Semaforo(Semaforo.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(semaforo);
            adicionarAtualizavel(semaforo);
        }
    }

    /**
     * Metodo reponsavel por gerar animais. 
     * 
     * @param random - objeto Random para gerar um numero aleatorio.
     * @param altura - alura maxima do mapa.
     * @param largura - largura maxima do mapa.
     * @param quantidadeItens - quantidade a ser gerada.
     */
    private void gerarAnimais(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < quantidadeItens;i++){
            Animal animal = new Animal(Animal.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(animal);
        }
    }
    
    /**
     * Metodo reponsavel por gerar veiculos e definir seus destinos inicias. 
     * 
     * @param random - objeto Random para gerar um numero aleatorio.
     * @param altura - alura maxima do mapa.
     * @param largura - largura maxima do mapa.
     * @param quantidadeItens - quantidade a ser gerada.
     */
    private void gerarVeiculos(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < 5;i++){
            Veiculo veiculo = new Veiculo(Veiculo.gerarLocalizaoAleatoria(random, largura, altura));
            veiculo.setLocalizacaoDestino(Veiculo.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(veiculo);
            adicionarAtualizavel(veiculo);
        } 
    }

    /**
     * Metodo responsavel por iniciar a simulacao.
     * 
     * @param numPassos - quantidade de passos que devem ser executados.
     */
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(500);
        }        
    }

    /**
     * Metodo responsavel por adicionar um atualizavel aa simulacao.
     * @param a - item atualizavel.
     */
    private void adicionarAtualizavel(Atualizavel a){
        atualizaveis.add(a);
    }

    /**
     * Metodo responsavel por atualizar estado dos atualizaveis.
     */
    private void executarUmPasso() {
        for(Atualizavel a : atualizaveis){
            mapa.removerItem((Item)a);
            a.executarAcao();
            mapa.adicionarItem((Item)a);
        }
        
        janelaSimulacao.executarAcao();
    }
    
    /**
     * Metodo responsavel controlar o tempo de cada passo.
     * @param milisegundos - intervalo de tempo em milisegundos.
     */
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
