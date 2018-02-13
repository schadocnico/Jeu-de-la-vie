import java.util.*;
/**
 * Created by nicolas.schad on 31/01/2018
 **/
public class JeuDeLaVie {
    public static void main(String[] args){
        ListeChainee<Couple> listeChainee = new ListeChainee<>();
        for (int i=-5;i < 5; i++){
            for (int j=-5;j < 5; j++){
                listeChainee.add(new Couple(i,j));
            }
        }
        System.out.println(listeChainee);
        ListeChainee<Couple> listeChainee2 = listeChainee.groupeCellule((o -> {
            if (o.getX() >= 0 && o.getX() <= 2)
                return (o.getY() >= 1 && o.getY() <= 3)?1:-1;
            else return -1;
        }));

        System.out.println("\n");
        System.out.println(listeChainee2);
    }
}
