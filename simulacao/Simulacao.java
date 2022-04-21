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
    
    public Simulacao() {
        mapa = Mapa.getMapa();
        atualizaveis = new ArrayList<Atualizavel>();
        gerarItens();
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
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
 
    private void gerarObras(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < quantidadeItens;i++){
            Obra obra = new Obra(Obra.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(obra);
        }
    }

    private void gerarSemaforos(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < quantidadeItens;i++){
            Semaforo semaforo = new Semaforo(Semaforo.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(semaforo);
            adicionarAtualizavel(semaforo);
        }
    }

    private void gerarAnimais(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < quantidadeItens;i++){
            Animal animal = new Animal(Animal.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(animal);
        }
    }

    private void gerarVeiculos(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < 5;i++){
            Veiculo veiculo = new Veiculo(Veiculo.gerarLocalizaoAleatoria(random, largura, altura));
            veiculo.setLocalizacaoDestino(Veiculo.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(veiculo);
            adicionarAtualizavel(veiculo);
        } 
    }

    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(500);
        }        
    }

    private void adicionarAtualizavel(Atualizavel a){
        atualizaveis.add(a);
    }

    private void executarUmPasso() {
        for(Atualizavel a : atualizaveis){
            mapa.removerItem((Item)a);
            a.executarAcao();
            mapa.adicionarItem((Item)a);
        }
        
        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
