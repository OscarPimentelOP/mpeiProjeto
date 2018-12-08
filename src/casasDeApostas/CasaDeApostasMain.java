package casasDeApostas;

import casasDeApostas.componentes.*;
import com.sun.org.apache.xpath.internal.SourceTree;
import componentes.CountingBloomFilter;
import componentes.DatasetReader;

import java.util.*;

import java.util.Set;

public class CasaDeApostasMain {

    public static void main(String[] args) {
        /*
        Match jogosDataStructure[] = DatasetReader.readMatches();
        ArrayList<Bookmaker> ListaDeCasas = geraCasas(3, jogosDataStructure);

        for (Bookmaker i : ListaDeCasas) {
            HashMap<Match, double[]> aux = i.getListaJogos();
            for (Map.Entry<Match, double[]> m : aux.entrySet()) {
                if (i.isMemberCBF(m.getKey())) {
                    System.out.println(m.getKey().getHome_team());
                    System.out.println(i.isMemberCBF(m.getKey()));
                    System.out.println(Arrays.toString(aux.get(m.getKey())));
                    System.out.println("probability win: " + i.probabilityOfCorrectOdd(m.getKey().getHome_team(), GameState.Win));
                }
            }
        }
        ArrayList<Bet> listaApostas = new ArrayList<>();
        CountingBloomFilter<String> apostasCorretas = new CountingBloomFilter<>(2, 2, 2);
        Gambler Jorge = new Gambler("Jorge", "Porto", listaApostas, apostasCorretas);
        //preciso método para aceder às casas criadas


        for (Bookmaker i : ListaDeCasas) {
            HashMap<Match, double[]> aux = i.getListaJogos();
            for (Match j : aux.keySet()) {
                Jorge.makeBet(j);
            }
        }
        */
        menu();

    }


    public static Set<Match> selecionaConjuntoJogos(Match[] jogosDS) {
        int number = (int) (Math.random() * (jogosDS.length - 1) + (1));
        // Match listaDeEJogosSDaCasa[]= new Match[number];
        Set<Match> conjuntoDeJoosDaCasa = new LinkedHashSet<Match>();
        System.out.println(jogosDS.length);

        for (int i = 0; i < number; i++) {
            int n = new Random().nextInt(jogosDS.length);
            Match m = jogosDS[n];
            //System.out.println(n);
            //System.out.println(m.getHome_team());
            conjuntoDeJoosDaCasa.add(m);
        }
        return conjuntoDeJoosDaCasa;
    }


    public static ArrayList<Bookmaker> geraCasas(int numeroDeCasas, Match[] jogosDS) {
        ArrayList<Bookmaker> listaDeCasas = new ArrayList<>();

        for (int i = 0; i < numeroDeCasas; i++) {
            Set<Match> jogosDaCasa = selecionaConjuntoJogos(jogosDS);
            Bookmaker casa = new Bookmaker(jogosDaCasa);
            listaDeCasas.add(casa);
        }
        return listaDeCasas;
    }

    public static void estimateAndPrintCorrectOdds(Bookmaker bookie, String fileName, String team, GameState gameState){
        // TODO - Function to get amtches of a certain club out of dataset - DONE
    }

    public static void listaCasas(ArrayList<Bookmaker> bookmakerList){
        for (Bookmaker b: bookmakerList
             ) {
            System.out.println(b);
        }
    }

    public static ArrayList<Gambler> geraApostadores(int n){
        Scanner input = new Scanner(System.in);
        ArrayList<Gambler> apostadores = new ArrayList<>();
        for (int i=0; i<n; i++){
            System.out.println("Nome: ");
            String nome = input.nextLine();
            System.out.println("Clube Favorito: ");
            String clube = input.nextLine();
            ArrayList<Bet> listaApostas = new ArrayList<>();
            CountingBloomFilter<String> apostasCorretas = new CountingBloomFilter<>(2, 2, 2);
            Gambler a = new Gambler(nome, clube, listaApostas, apostasCorretas);
           apostadores.add(a);

        }

        return apostadores;

    }

    public  static void imprimeApostadores(ArrayList<Gambler> apostadores){
        for (Gambler g: apostadores
             ) {System.out.println(g);}
    }

    public static void fazApostadorApostar(Gambler apostador, Bookmaker casa, Match jogo){
        do{
            System.out.println("Qual o jogo em que quer que " + apostador + "aposte?");
            
        }while (!casa.isMemberCBF(jogo))

    }

    public static void menu() {

        Scanner inputScanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 6) {
            System.out.println("---------------------------------------------");
            System.out.println("|||           Casas de Apostas           |||");
            System.out.println("---------------------------------------------");
            System.out.println("|| 1-> Gerar casas de apostas              ||");
            System.out.println("|| 2-> Listar as casas geradas             ||");
            System.out.println("|| 3-> Criar um apostador                  ||");
            System.out.println("|| 4-> Fazer um jogador apostar            ||");
            System.out.println("|| 5->                                     ||");
            System.out.println("|| 6-> Terminar programa                   ||");
            System.out.println("---------------------------------------------");
            System.out.print("Insira a sua opção->");

            try {
                opcao = inputScanner.nextInt();
            } catch (Exception var4) {
                System.out.println("Opção inválida!");
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    Match jogosDataStructure[] = DatasetReader.readMatches();
                    ArrayList<Bookmaker> listaDeCasas = geraCasas(1, jogosDataStructure);
                    listaCasas(listaDeCasas);
                    break;
                case 2:

                    break;
                case 3:
                    ArrayList<Gambler> listaDeApostadores =geraApostadores(1);
                    imprimeApostadores(listaDeApostadores);
                    break;
                case 4:

                case 5:

                    break;
                case 6:
                    System.out.println("Programa terminado com sucesso!");

            }


        }
    }
}