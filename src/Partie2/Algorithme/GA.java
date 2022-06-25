package Partie2.Algorithme;

import java.io.FileWriter;
import java.io.IOException;
import src.Puzzle;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class GA {

    public SortedSet<Chromosome> population = new TreeSet<Chromosome>();
    public int MAX_POP_SIZE;
    public double cross_rate, mut_rate;
    public static String[] Moves = {"U", "D", "L", "R"};
    public float pop_fitness;
    public int pop_size;
    public static int MIN_LENGTH = 6;
    public int iterations;

    //Selection function. (Roulette selection)
    public Chromosome[] selectPair() {
        double proba;
        Chromosome[] parents = new Chromosome[2];
        float proba_sum;
        int i;
        do {
            i = 0;
            while (i < 2) {
                proba_sum = 0;
                proba = ThreadLocalRandom.current().nextDouble(1);
                for (Chromosome c : population) {

                    if (c.getFitness() == 1.0) {
                        break;
                    }
                    proba_sum = proba_sum + (c.getFitness() / pop_fitness);
                    if (proba <= proba_sum && c.getFitness() != 1.0) {
                        parents[i] = c;
                        i++;
                        break;
                    }
                }
            }
        } while (parents[0].equals(parents[1]));
        return parents;
    }

    public Chromosome[] crossOver(Chromosome p1, Chromosome p2) {
        double proba = ThreadLocalRandom.current().nextDouble(1);

        if (proba < cross_rate) {
            return p1.crossOver(p2);
        } else {
            return new Chromosome[]{new Chromosome(p1), new Chromosome(p2)};
        }
    }

    //generate valid chromosome.	
    private Chromosome generateChromosome() {
        int idx = 0;
        Puzzle p = new Puzzle(Chromosome.INITIAL_STATE.getState());
        String path = "";
        LinkedList<Character> possible_moves;
        for (int i = 0; i < MIN_LENGTH; i++) {
            possible_moves = p.getPossibleMoves();
            idx = ThreadLocalRandom.current().nextInt(possible_moves.size());
            path = path + possible_moves.get(idx);
            p = p.goToState("" + possible_moves.get(idx));
        }
        return new Chromosome(path, false);
    }

    public void initPopulation() {
        Chromosome c;
        while (population.size() < MAX_POP_SIZE) {
            c = generateChromosome();
            population.add(c);
        }
        pop_fitness = populationFitness();
    }

    public String runEvolution(int nb_generations) {
        Chromosome[] parents, children;
        SortedSet<Chromosome> next_gen;
        int j;
        for (int i = 0; i < nb_generations; i++) {
            next_gen = new TreeSet<Chromosome>();

            if (population.first().getFitness() == 3) {
                //System.out.println( population.first().getFitness() + " " +  population.first() + " " + i);
                iterations = i;
                return population.first().getGenes();
            }

            j = 0;
            //keep 20% of current generation and pass them to the next generation as elites.
            for (Chromosome c : population) {
                Chromosome c2 = new Chromosome(c);
                c2.grow(mut_rate);
                if (c2.LENGTH >= 32) {
                    c2.truncate();
                }
                next_gen.add(c2);
                j++;
                if (j >= population.size() * 0.2) {
                    break;
                }
            }

            while (j < MAX_POP_SIZE - 2) {
                parents = selectPair();
                children = crossOver(parents[0], parents[1]);
                children[0].grow(mut_rate);
                children[1].grow(mut_rate);
                if (children[0].LENGTH >= 32) {
                    children[0].truncate();
                }
                if (children[1].LENGTH >= 32) {
                    children[1].truncate();
                }
                next_gen.add(children[0]);
                next_gen.add(children[1]);

                j = j + 2;
            }
            population = next_gen;
            pop_fitness = populationFitness();
            pop_size = j;
            System.out.println("Nombre de chromosome :" + pop_size);
            System.out.println("nombre de générarion :" + iterations);
        }

        return "X";
    }

    public float populationFitness() {
        float sum = 0;
        for (Chromosome c : population) {
            sum = sum + c.getFitness();
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Chromosome.INITIAL_STATE = new Puzzle(524631798);
        GA s = new GA();
        FileWriter resultsGA = null;
        resultsGA = new FileWriter("resultsGA.csv");
        resultsGA.write("initial state;Nombre max de génération ; Nombre de génération; Taux de mutation; Taux de croisement; Taille  de la Solution   ; temp d'execution \n");
        // String header []={"initial state","Nombre max de génération" , "Nombre de génération", "Taux de mutatio", "Taux de croisement", "Taille  de la Solution",   " temp d'execution \n"};
        String solution = "";
        long starttime = 0, endtime = 0;
        for (int i = 100; i < 15000; i = i + 100) {
            for (int h = 200; h < 1000; h = h + 100) {
                for (double j = 0.4; j < 0.7; j = j + 0.1) {
                    for (double k = 0.4; k < 0.7; k = k + 0.1) {
                        s.MAX_POP_SIZE = h;
                        s.cross_rate = j;
                        s.mut_rate = k;
                        starttime = System.currentTimeMillis();
                        s.initPopulation();
                        String p = s.runEvolution(i);
                        solution = s.runEvolution((Integer) s.iterations);
                        endtime = System.currentTimeMillis();
                        System.out.println(Chromosome.INITIAL_STATE.goToState(p));
                        System.out.println("Nombre max de génération :" + s.MAX_POP_SIZE);
                        System.out.println("Nombre de génération : :" + s.iterations);
                        System.out.println("Taille d'une génération :" + i);
                        System.out.println("Taux de mutation:" + s.mut_rate);
                        System.out.println("Taux de croisement: :" + s.cross_rate);
                        System.out.println("Nombre de chromosome :" + s.pop_size);
                        System.out.println("Taille  de la Solution ::" + solution.length());
                        System.out.println("temp d'execution :" + (endtime - starttime));
                        System.out.println("solution  :" + solution);
                        System.out.println("-------------------------------------------------");
                        resultsGA.write("524631798" + "; "
                                + s.MAX_POP_SIZE + "; "
                                + s.iterations + "; "
                                + i
                                + s.mut_rate
                                + s.cross_rate
                                + s.pop_size + "; "
                                + solution.length() + "; "
                                + (endtime - starttime) + "\n"
                        );
                    }
                }
            }

        }

    }

}
