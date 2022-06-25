package puzzle8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

public class Configuration {
    private int [][] easy = new int[3][3];
    private int [][] medium = new int[3][3];
    private int [][] hard = new int[3][3];
   

//    public static void main(String[] args) {
//
//        generation("Easy"); //modes : Easy, Medium, Hard, Random
//        affichage(easy);
//
//        generation("Medium"); //modes : Easy, Medium, Hard, Random
//        affichage(medium);
//
//
//        generation("Hard"); //modes : Easy, Medium, Hard, Random
//        affichage(hard);
//
//
//        generation("Random"); //modes : Easy, Medium, Hard, Random
//        affichage(random);
//
//
//    }
    public int[][] getEasy(){
        return this.easy;
    }
    public int[][] getMedium(){
        return this.medium;
    }
    public int[][] getHard(){
        return this.hard;
    }

    public  void generation(String mode ) {

        switch(mode){
            case "Easy":
                //généré un taquin aléatoirement, avec 5 carreaux bien placés
                this.easy = Randomize(5);
                //test si l'etat généré est solvable + le rends solvable dans le cas contraire
                estSolvable(this.easy);
                break;
            case "Medium":
                this.medium = Randomize(3);
                estSolvable(this.medium);
                break;
            case "Hard":
                this.hard = Randomize(0);
                estSolvable(this.hard);
                break;
           
         }

    }

    


    public static int[][] Randomize(int nbr_carreaux) {
        int i,j;
        //tableau qu'on veut générer
        int[][] array = new int[3][3];
        //tableau qui contiendra les carreaux bien placé
        int[] carreaux_correctes = new int[nbr_carreaux];

        //Liste qu'on utilisera avec random pour générer les carreaux
        ArrayList<Integer> list = new ArrayList<Integer>(9);
        for (i = 0; i < 9; i++) { list.add(i); }

        //tableau qui contient tout les valeurs du taquin en bon emplacement
        int[] v = {1,2,3,8,0,4,7,6,5};
        Random rand = new Random();

        //Séparation des carreaux qu'on veut bien placer de la liste
        //pour les mettre à part lors de la génération
        i = 0;
        for(j=0; j < nbr_carreaux; j++) {
            int index = rand.nextInt(list.size());
            carreaux_correctes[i] = list.remove(index);
            i++;
        }

        //Génération aléatoire du taquin
        //en gardant un certain nbr de carreaux à leurs places
        i = 0;
        while (list.size() > 0) {
            int index = rand.nextInt(list.size());
            while(existe(v[i], carreaux_correctes) == true){
                i++;
            }
            v[i] = list.remove(index);
            i++;
        }
        //conversion du tableau en matrice
        for(i=0; i<3 ; i++) {
            for(j=0; j<3 ; j++) {
                array[i][j] = v[i*3+j];
            }
        }

        return array;
    }




        // cette fonction vérifie la solvabilité d'un etat donné,
        // et transforme l'etat insolvable à un etat solvable
        public static int[][] estSolvable(int[][] puzzle)
        {
            // Cette table contient les paires de carreaux dont on doit comparer l'indice
            int [][] table_comparaison = {{1, 2}, {2, 3}, {3, 8}, {8, 0}, {0, 4}, {4, 7}, {7, 6}, {6, 5},
                    {1, 3}, {2, 8}, {3, 0}, {8, 4}, {0, 7}, {4, 6}, {7, 5},
                    {1, 8}, {2, 0}, {3, 4}, {8, 7}, {0, 6}, {4, 5},
                    {1, 0}, {2, 4}, {3, 7}, {8, 6}, {0, 5},
                    {1, 4}, {2, 7}, {3, 6}, {8, 5},
                    {1, 7}, {2, 6}, {3, 5},
                    {1, 6}, {2, 5},
                    {1, 5}};

            // Ce dictionnaire contient les indices des nombres du taquin (nombre , indice)
            HashMap<Integer, Integer> dictionaire_puzzle = new HashMap<Integer, Integer>();
            for(int i=0; i<3 ; i++) {
                for(int j=0; j<3 ; j++) {
                    dictionaire_puzzle.put(puzzle[i][j], i*3+j);
                }
            }

            int nbr_inversion = 0;
            int indice_a_inverser = 0;
            // Compter les inversions du taquin
            for(int i=0; i<table_comparaison.length ; i++) {
                if(dictionaire_puzzle.get(table_comparaison[i][0]) > dictionaire_puzzle.get(table_comparaison[i][1])){
                    nbr_inversion++;
                    indice_a_inverser = i; //garder un indice pour plus tard
                }
            }

            if((nbr_inversion % 2 == 0))
                System.out.println("Solvable");
            else
                System.out.println("Insolvable");

            //Rendre l'etat Insolvable solvable, en diminuant le nombre d'inversions par 1
            if(nbr_inversion % 2 != 0) {
                System.out.println("traitement en cours pour le transformer en etat solvable..");
                int valeur1 = dictionaire_puzzle.get(table_comparaison[indice_a_inverser][0]);
                int valeur2 = dictionaire_puzzle.get(table_comparaison[indice_a_inverser][1]);

                dictionaire_puzzle.put(table_comparaison[indice_a_inverser][0], valeur2);
                dictionaire_puzzle.put(table_comparaison[indice_a_inverser][1], valeur1);
            }
            System.out.println("fin");

            int[] arr = new int[9];
            int k = 0;

            for(Entry<Integer, Integer> entry: dictionaire_puzzle.entrySet()) {
                arr[entry.getValue()] = entry.getKey();
            }

            for(int i=0; i<3 ; i++) {
                for(int j=0; j<3 ; j++) {
                    puzzle[i][j] = arr[k];
                    k++;

                }
            }
            //retourne un taquin solvable
            return puzzle;
        }

        //cette fonction vérifie si une valeur existe dans un tableau
        public static boolean existe(int val, int[] arr) {
            boolean bool = false;
            for(int i=0; i< arr.length ;i++){
                if(arr[i]==val) {bool=true ; break;}}
            return bool;
        }

        //affichage de la matrice
        public static void affichage(int[][] arr){
            for(int i=0; i<3 ; i++) {
                for(int j=0; j<3 ; j++) {
                    System.out.print(arr[i][j]);
                    System.out.print(" ");
                }
                System.out.println("");
            }
            System.out.println("");
        }



    }
