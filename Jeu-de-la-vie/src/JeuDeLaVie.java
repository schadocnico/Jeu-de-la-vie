import javax.swing.*;

/**
 * Created by nicolas.schad on 31/01/2018
 **/
public class JeuDeLaVie extends JFrame{
    public static void main(String[] args){
        ListeChainee<Couple> list = Liff.lecture("HOTEL.LIF");
        /*for(int a=0; a<=100; a++){
            for(int b=0; b<=100; b++){
                list.add(new Couple(a,b));
            }
        }*/
        /*list.add(new Couple(0,0,10));
        list.add(new Couple(2,0,10));
        list.add(new Couple(1,1,10));
        list.add(new Couple(2,1,10));
        list.add(new Couple(1,2,10));*/
        long debut = System.currentTimeMillis();
        list = Generation2.calculeVoisins(list);// A LAISSER, PREMIER CALCULE
        long time = System.currentTimeMillis()-debut;
        System.out.println("Calcule 1 :" + time);

        /*long debut2 = System.currentTimeMillis();
        Generation2.newGeneration(list);
        long time2 = System.currentTimeMillis()-debut2;
        System.out.println("Calcule 2 :" + time2);

        long debut3 = System.currentTimeMillis();
        Generation2.newGeneration(list);
        long time3 = System.currentTimeMillis()-debut3;
        System.out.println("Calcule 3 :" + time3);*/


        // Interface graphique, list = la list de la 1ere generation, nb_generation = nombre de generation a faire
        new JeuDeLaVie(list, 50);
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
        System.out.println(list);
        System.out.println(nb_generation);
        for(int i=0; i < nb_generation; i++) {
            System.out.println(i + " : " + list);
            pan.setList(list); // On lui donne la liste a dessiner
            pan.repaint(); // On redessine notre fenetre
            //System.out.println(list);
            list = Generation2.newGeneration(list);
            // Comme on dit : la pause s'impose !
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
