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
     * Cria uma simulação.
     * Atribui mapa, atualizaveis, gera itens e cria a janela de simulação.
     */
    public Simulacao() {
        mapa = Mapa.getMapa();
        atualizaveis = new ArrayList<Atualizavel>();
        gerarItens();
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    /**
     * Método responsável por gerar todos os ítens aleatoriamente dentro dos limites do mapa.
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
     * Método reponsável por gerar obras. 
     * 
     * @param random - objeto Random para gerar um número aleatório.
     * @param altura - alura máxima do mapa.
     * @param largura - largura máxima do mapa.
     * @param quantidadeItens - quantidade a ser gerada.
     */
    private void gerarObras(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < quantidadeItens;i++){
            Obra obra = new Obra(Obra.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(obra);
        }
    }

    /**
     * Método reponsável por gerar semáforos. 
     * 
     * @param random - objeto Random para gerar um número aleatório.
     * @param altura - alura máxima do mapa.
     * @param largura - largura máxima do mapa.
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
     * Método reponsável por gerar animais. 
     * 
     * @param random - objeto Random para gerar um número aleatório.
     * @param altura - alura máxima do mapa.
     * @param largura - largura máxima do mapa.
     * @param quantidadeItens - quantidade a ser gerada.
     */
    private void gerarAnimais(Random random, int altura,int largura, int quantidadeItens){
        for(int i = 0; i < quantidadeItens;i++){
            Animal animal = new Animal(Animal.gerarLocalizaoAleatoria(random, largura, altura));
            mapa.adicionarItem(animal);
        }
    }
    
    /**
     * Método reponsável por gerar veículos e definir seus destinos inicias. 
     * 
     * @param random - objeto Random para gerar um número aleatório.
     * @param altura - alura máxima do mapa.
     * @param largura - largura máxima do mapa.
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
     * Método responsável por iniciar a simulação.
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
     * Método responsável por adicionar um atualizável à simulação.
     * @param a - item atualizável.
     */
    private void adicionarAtualizavel(Atualizavel a){
        atualizaveis.add(a);
    }

    /**
     * Método responsável por atualizar estado dos atualizaveis.
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
     * Método resposável controlar o tempo de cada passo.
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
