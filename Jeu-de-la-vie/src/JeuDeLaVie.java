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
        Couple c1 = new Couple(1,2);
        ListeChainee<Couple> listeChainee2 = listeChainee.selection((o -> {
            if (o.getX() != c1.getX() && o.getY() != c1.getY()){
                if (o.getX() >= c1.getX()-1 && o.getX() <= c1.getX()+1)
                    return (o.getY() >= c1.getY()-1 && o.getY() <= c1.getY()+1);
            } else return false;
        }));

        System.out.println("\n");
        System.out.println(listeChainee2);
    }
}
