import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;

/**
 * Created by nicolas.schad on 31/01/2018
 **/
public class JeuDeLaVie extends JFrame{
    private Panneau pan = new Panneau();

    public static void main(String[] args){
        if (args.length==0){
            System.err.println("Aucune commande, tapez -h pour connaitre la liste des commandes.");
            return;
        }
        if(args[0].equals("-name")){
            System.out.println("Arnaud Pasquier / Nicolas Schad / Thibaut Maziere");
        } else if(args[0].equals("-h")){
            System.out.println(" -name: affiche les noms et prénoms");
            System.out.println(" -h : affiche la liste des options du programme");
            System.out.println(" -s d fichier.lif : exécute une simulation du jeu d une durée d affichant les configurations du jeu avec le numéro de génération");
            System.out.println(" -c max fichier.lif : calcule le type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul. ");
            System.out.println(" -w max dossier : calcule le type d’évolution de tous les jeux contenus dans le dossier passé en paramètre");
        } else if (args.length==3) switch (args[0]) {
            case "-s":
                ListeChainee<Couple> list;
                try {
                    list = Liff.lecture(args[2]);
                } catch (IOException e) {
                    System.err.println(e);
                    return;
                }
                new JeuDeLaVie(list, Integer.parseInt(args[1]));
                break;
            case "-c":
                ListeChainee<Couple> list1;
                try {
                    list1 = Liff.lecture(args[2]);
                } catch (IOException e) {
                    System.err.println(e);
                    return;
                }
                Comportement c = algo(list1, Integer.parseInt(args[1]));
                System.out.println(args[2] + " -> " + c);
                break;
            case "-w":
                String[] rep = listerRepertoire(args[2]);
                for (int i=0;i<rep.length;i++){
                    ListeChainee<Couple> list2;
                    try {
                        list2 = Liff.lecture(args[2]+"/"+rep[i]);
                    } catch (IOException e) {
                        System.err.println(e);
                        return;
                    }
                    Comportement c1 = algo(list2, Integer.parseInt(args[1]));
                    System.out.println(rep[i] + " -> " + c1);
                }
                break;
            default:
                System.err.println("Erreur, tapez -h pour connaitre la liste des commandes.");
        }
        else {
            System.err.println("Erreur, tapez -h pour connaitre la liste des commandes.");
        }
    }

    public JeuDeLaVie(ListeChainee<Couple> list, int nb_generation) { // CHANGER LE TAILLE DE LA FENETRE EN FONCTION DU NOMBRE DE CELLULES???
        this.setTitle("Animation du jeu de la vie");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(pan);
        this.setVisible(true);

        go(list, nb_generation);
    }
    
    
    private void go(ListeChainee<Couple> list, int nb_generation) {
        System.out.println(list);
        System.out.println(nb_generation);
        for(int i=0; i < nb_generation; i++) {
            System.out.println(i + " : " + list);
            pan.setList(list); // On lui donne la liste a dessiner
            pan.repaint(); // On redessine notre fenetre
            list = Generation2.newGeneration(list);
            // la pause s'impose
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private static Comportement algo(ListeChainee<Couple> list, int max){
        ListeChainee<Couple> list2 = Generation2.newGeneration(list);
        for (int e=0;e<=max;e++){
            list = Generation2.newGeneration(list);
            list2 = Generation2.newGeneration(list2);
            list2 = Generation2.newGeneration(list2);
            ListeChainee<Couple> list2T = list2.copy();
            ListeChainee<Couple> listT = list.copy();
            list2T.supprimer(((Couple o) -> o.getNbVoisins()<10));
            listT.supprimer(((Couple o) -> o.getNbVoisins()<10));
            //System.out.println("List gen " + e + " : " + list);
            //System.out.println("List gen " + e + " : " + list2);
            if (listT.equals(list2T)) {
                list2 = Generation2.newGeneration(list2);
                list2T = list2.copy();
                list2T.supprimer(((Couple o) -> o.getNbVoisins()<10));
                if (listT.equals(list2T))
                    return Comportement.Stable;
                return Comportement.Oscillateur;
            }
            int listX = listT.premierElement().getX();
            int listY = listT.premierElement().getY();
            int list2X = list2T.premierElement().getX();
            int list2Y = list2T.premierElement().getY();
            int diffX = listX-list2X;
            int diffY = listY-list2Y;

            //System.out.println("List gen " + e + " : " + list2T + diffX + ":" + diffY + ";" + listX + ":" + list2X);
            //System.out.println("List gen " + e + " : " + listT + diffX + ":" + diffY + ";" + listX + ":" + list2X);
            Iterator<Couple> it = list2T.iterator();
            while (it.hasNext()){
                Couple c = it.next();
                c.setX(c.getX()+diffX);
                c.setY(c.getY()+diffY);
            }

            if (listT.equals(list2T))
                return Comportement.Vaisseau;

        }
        return Comportement.Inconnu;
    }

    public static String[] listerRepertoire(String rep){
        return new File(rep).list();
    }


}
