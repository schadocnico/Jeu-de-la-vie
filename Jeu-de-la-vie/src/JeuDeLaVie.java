import javax.swing.*;
/**
 * Created by nicolas.schad on 31/01/2018
 **/
public class JeuDeLaVie extends JFrame{
    public static void main(String[] args){
        ListeChainee<Couple> list = new ListeChainee<>();
        list.add(new Couple(0,1));
        list.add(new Couple(1,1));
        list.add(new Couple(1,2));
        list.add(new Couple(2,1));
        list.add(new Couple(2,2));
        new JeuDeLaVie(list, 1);
    }

    private Panneau pan = new Panneau();

    public JeuDeLaVie(ListeChainee<Couple> list, int nb_generation) {
        this.setTitle("Animation du jeu de la vie");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(pan);
        this.setVisible(true);

        go(list, nb_generation);
    }

    private void go(ListeChainee<Couple> list_depart, int nb_generation) {

        pan.setList(list_depart);
        // On redessine notre Panneau

        pan.repaint();

        // Comme on dit : la pause s'impose !
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
