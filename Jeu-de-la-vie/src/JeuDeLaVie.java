import javax.swing.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nicolas.schad on 31/01/2018
 **/
public class JeuDeLaVie extends JFrame{
    public static void main(String[] args){
        ListeChainee<Couple> list = new ListeChainee<>();
        list.add(new Couple(0,0));
        list.add(new Couple(0,1));
        list.add(new Couple(0,2));
        list.add(new Couple(1,0));
        list.add(new Couple(1,1));
        list.add(new Couple(1,2));
        list.add(new Couple(2,0));
        list.add(new Couple(2,1));
        list.add(new Couple(2,2));


        ListeChainee<Couple> list2 = Generation.newGeneration(list);


        // Interface graphique, list = la list de la 1ere generation, nb_generation = nombre de generation a faire
        new JeuDeLaVie(list, 10);
    }

    private Panneau pan = new Panneau();

    public JeuDeLaVie(ListeChainee<Couple> list, int nb_generation) { // CHANGER LE TAILLE DE LA FENETRE EN FONCTION DU NOMBRE DE CELLULES???
        this.setTitle("Animation du jeu de la vie");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(pan);
        this.setVisible(true);

        go(list, nb_generation);
    }
    
    
    private void go(ListeChainee<Couple> list, int nb_generation) { // PASSER LISTECHAINNE<LISTECHAINEE<COUPLE>> ???
        long debut = System.currentTimeMillis();
        System.out.println(list);
        for(int i=0; i < nb_generation; i++) {
            pan.setList(list); // On lui donne la liste a dessiner
            pan.repaint(); // On redessine notre fenetre
            System.out.println(list);
            list = Generation.newGeneration(list);
            // Comme on dit : la pause s'impose !
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        long time = System.currentTimeMillis()-debut;
        System.out.println(time);

    }

}
