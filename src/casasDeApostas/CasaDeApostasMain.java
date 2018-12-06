package casasDeApostas;

import casasDeApostas.componentes.Bet;
import casasDeApostas.componentes.Bookmaker;
import casasDeApostas.componentes.Gambler;
import casasDeApostas.componentes.Match;
import componentes.CountingBloomFilter;
import componentes.DatasetReader;

import java.util.*;

import java.util.Set;

public class CasaDeApostasMain {

    public static void main(String[] args) {
        Match jogosDataStructure[] = DatasetReader.readMatches();
        ArrayList<Bookmaker> ListaDeCasas = geraCasas(1, jogosDataStructure);

        for(Bookmaker i: ListaDeCasas){
            HashMap<Match,double[]> aux = i.getListaJogos();
            for(Map.Entry<Match, double[]> m : aux.entrySet()){
                    if(i.checkCBF(m.getKey().getHome_team()) > 0) {
                        System.out.println(m.getKey().getHome_team());
                        System.out.println(i.checkCBF(m.getKey().getHome_team()));
                        System.out.println(Arrays.toString(aux.get(m.getKey())));
                    }
                }
            }
        ArrayList<Bet> listaApostas =new ArrayList<>();
        CountingBloomFilter<String> apostasCorretas = new CountingBloomFilter<>(2,2,2);
        Gambler Jorge = new Gambler("Jorge", "Porto", listaApostas, apostasCorretas);
        //preciso método para aceder às casas criadas


        for(Bookmaker i: ListaDeCasas){
            HashMap<Match,double[]> aux = i.getListaJogos();
                for(Match j: aux.keySet()){
                    Jorge.makeBet(j);
                }
        }

        }


    public static Set<Match> selecionaConjuntoJogos(Match[] jogosDS){
        int number = (int) (Math.random()*(jogosDS.length-1)+(1));
       // Match listaDeEJogosSDaCasa[]= new Match[number];
        Set<Match> conjuntoDeJoosDaCasa = new LinkedHashSet<Match>();
        System.out.println(jogosDS.length);

        for(int i=0; i< number; i++){
           int n =  new Random().nextInt(jogosDS.length);
           Match m = jogosDS[n];
           //System.out.println(n);
           //System.out.println(m.getHome_team());
           conjuntoDeJoosDaCasa.add(m);
        }
        return conjuntoDeJoosDaCasa;
    }



    public static ArrayList<Bookmaker> geraCasas(int numeroDeCasas, Match[] jogosDS){
        ArrayList<Bookmaker> listaDeCasas = new ArrayList<>();

        for(int i =0; i< numeroDeCasas; i++){
            Set <Match> jogosDaCasa = selecionaConjuntoJogos(jogosDS);
            Bookmaker casa = new Bookmaker(jogosDaCasa);
            listaDeCasas.add(casa);
        }
        return listaDeCasas;
}





}