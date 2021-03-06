import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;

/**
 * Created by nicolas.schad on 31/01/2018
 **/
public class JeuDeLaVie extends JFrame{
    private Panneau pan = new Panneau();
    public static int typeMonde = 0; //0 infini;1 fini; 2 circulaire

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Aucune commande, tapez -h pour connaitre la liste des commandes.");
            return;
        }
        if (args[0].equals("-name")) {
            System.out.println("Arnaud Pasquier / Nicolas Schad / Thibaut Maziere");
        } else if (args[0].equals("-h")) {
            System.out.println(" -name: affiche les noms et prénoms");
            System.out.println(" -h : affiche la liste des options du programme");
            System.out.println(" -s d fichier.lif : exécute une simulation du jeu d une durée d affichant les configurations du jeu avec le numéro de génération");
            System.out.println(" -c max fichier.lif : calcule le type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul. ");
            System.out.println(" -w max dossier : calcule le type d’évolution de tous les jeux contenus dans le dossier passé en paramètre");
            System.out.println(" Ajouter apres la commande -f pour un monde fini et -c pour un monde circulaire sinon rien pour un monde infinie suivi de 4 coordonées : haut droite bas gauche ");
        } else if (args.length >= 3){
            if (args.length == 8){
                if (args[3].equals("-f")) {
                    typeMonde = 1;
                    Generation2.typeMonde=1;
                } else if (args[3].equals("-c")){
                    typeMonde = 2;
                    Generation2.typeMonde=2;
                }
                Generation2.haut = Integer.parseInt(args[4]);
                Generation2.droite = Integer.parseInt(args[5]);
                Generation2.bas = Integer.parseInt(args[6]);
                Generation2.gauche = Integer.parseInt(args[7]);

            }
            switch (args[0]) {
                case "-s":
                    ListeChainee<Couple> list;
                    try {
                        list = Liff.lecture(args[2]);
                    } catch (IOException e) {
                        System.err.println(e);
                        return;
                    }
                    System.out.println(Generation2.typeMonde);
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
                    for (int i = 0; i < rep.length; i++) {
                        ListeChainee<Couple> list2;
                        try {
                            list2 = Liff.lecture(args[2] + "/" + rep[i]);
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private static Comportement algo(ListeChainee<Couple> list1, int max){
        ListeChainee<Couple> list = list1.copy();
        ListeChainee<Couple> list2 = Generation2.newGeneration(list);
        for (int e=0;e<=max;e++){
            list = Generation2.newGeneration(list);
            list2 = Generation2.newGeneration(list2);
            list2 = Generation2.newGeneration(list2);
            ListeChainee<Couple> list2T = list2.copy();
            ListeChainee<Couple> listT = list.copy();
            if (list2T == null || list2T.isEmpty())
                return calcule(list1, list2, Comportement.Mort);
            list2T.supprimer(((Couple o) -> o.getNbVoisins()<10));
            listT.supprimer(((Couple o) -> o.getNbVoisins()<10));
            //System.out.println("List gen " + e + " : " + list);
            //System.out.println("List gen " + e + " : " + list2);
            if (listT.isEmpty())
                return Comportement.Mort;
            if (listT.equals(list2T)) {
                list2 = Generation2.newGeneration(list2);
                list2T = list2.copy();
                list2T.supprimer(((Couple o) -> o.getNbVoisins()<10));
                if (listT.equals(list2T)) {
                    return calcule(list1, list2, Comportement.Stable);
                }
                return calcule(list1, list, Comportement.Oscillateur);
            }

            diff(listT, list2T);

            if (listT.equals(list2T))
                return calcule(list1, list2, Comportement.Vaisseau);

        }
        return Comportement.Inconnu;
    }

    private static Couple diff(ListeChainee<Couple> l1, ListeChainee<Couple> l2){
        int listX = l1.premierElement().getX();
        int listY = l1.premierElement().getY();
        int list2X = l2.premierElement().getX();
        int list2Y = l2.premierElement().getY();
        int diffX = listX-list2X;
        int diffY = listY-list2Y;

        //System.out.println("List gen " + e + " : " + list2T + diffX + ":" + diffY + ";" + listX + ":" + list2X);
        //System.out.println("List gen " + e + " : " + listT + diffX + ":" + diffY + ";" + listX + ":" + list2X);
        Iterator<Couple> it = l2.iterator();
        while (it.hasNext()){
            Couple c = it.next();
            c.setX(c.getX()+diffX);
            c.setY(c.getY()+diffY);
        }

        return new Couple(diffX,diffY);

    }

    private static Comportement calcule(ListeChainee<Couple> listeBase, ListeChainee<Couple> listeTermine, Comportement c){
        if (listeTermine == null || listeTermine.isEmpty()) {
            int nbPassage = 0;
            while (listeBase != null && !listeBase.isEmpty()){
                listeBase = Generation2.newGeneration(listeBase);
                nbPassage++;
            }
            c.setQueue(nbPassage);
            return c;
        }
        ListeChainee<Couple> listTT = listeTermine.copy();
        listTT.supprimer(((Couple o) -> o.getNbVoisins()<10));
        ListeChainee<Couple> list1 = listeBase.copy();
        ListeChainee<Couple> list1T = list1.copy();
        list1T.supprimer(((Couple o) -> o.getNbVoisins()<10));
        ListeChainee<Couple> list2T = list1T.copy();
        diff(listTT,list2T);
        boolean passage = false;
        int nbPassage = 0;
        int nbQueue = 1;
        if (list1T.equals(listTT)) {
            passage = true;
            nbPassage++;
        }
        Couple diff1 = new Couple(0,0);
        for (int i =0;i<=100;i++){
            list1T = list1.copy();
            list1T.supprimer(((Couple o) -> o.getNbVoisins()<10));
            list2T = list1T.copy();
            Couple diff = diff(listTT,list2T);
            if (list1T.equals(listTT)){
                if (passage) break;
                passage = true;
                nbPassage++;
                list1 = Generation2.newGeneration(list1);
            } else if (listTT.equals(list2T)){
                if (passage) break;
                passage = true;
                nbPassage++;
                diff1 = diff;
                list1 = Generation2.newGeneration(list1);
            } else {
                if (!passage)
                    nbQueue++;
                else
                    nbPassage++;
                list1 = Generation2.newGeneration(list1);
            }
        }
        //System.out.println(nbPassage + " " + (nbQueue-nbPassage));
        c.setPeriode(nbPassage);
        c.setQueue((nbQueue-nbPassage));
        c.setDeplacement(diff1);
        return c;
    }

    public static String[] listerRepertoire(String rep){
        return new File(rep).list();
    }


}
