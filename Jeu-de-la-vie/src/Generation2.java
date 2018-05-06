import java.util.Iterator;

public class Generation2 {

    public static ListeChainee<Couple> newGeneration(ListeChainee<Couple> list){
        if (list==null || list.isEmpty())
            return new ListeChainee<>();
        ListeChainee<Couple> newList = list.copy();

        Iterator<Couple> it = list.iterator();


        while (it.hasNext()){
            Couple ATester = it.next();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (ATester.getNbVoisins() == 3){
                        if (i==0 && j==0){
                            newList.add(new Couple(ATester.getX(), ATester.getY(), 10));
                        }else {
                            newList.add(new Couple(ATester.getX()+i, ATester.getY()+j, 1));
                        }
                    } else if (ATester.getNbVoisins() >= 10 && ATester.getNbVoisins() != 12 && ATester.getNbVoisins() != 13){
                        if (i==0 && j==0){
                            newList.add(new Couple(ATester.getX(), ATester.getY(), -10));
                        }else {
                            newList.add(new Couple(ATester.getX()+i, ATester.getY()+j, -1));
                        }
                    }

                }
            }

        }
        if (newList!=null && !(newList.isEmpty()))
            newList.supprimer(((Couple o) -> o.getNbVoisins()<=0));
        return newList;
    }

    public static ListeChainee<Couple> calculeVoisins(ListeChainee<Couple> list){
        Iterator<Couple> it = list.iterator();
        ListeChainee<Couple> voisins = new ListeChainee<>();
        while (it.hasNext()){
            Couple ATester = it.next();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i!=0 || j!=0 ){
                        voisins.add(new Couple(ATester.getX()+i, ATester.getY()+j, 1));
                    } else {
                        voisins.add(new Couple(ATester.getX(), ATester.getY(), 10));
                    }
                }
            }

        }
        return voisins;

    }

}