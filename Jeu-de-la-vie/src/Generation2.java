import java.util.Iterator;

public class Generation2 {
    public static int bas;
    public static int haut;
    public static int gauche;
    public static int droite;
    public static int typeMonde;

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
        maj(newList);
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
        maj(voisins);
        return voisins;

    }

    public static void maj(ListeChainee<Couple> list){
        if (typeMonde==0) return;
        if (typeMonde==1){
            list.supprimer((Couple o) -> o.getX()<gauche || o.getY()<bas || o.getY()>haut || o.getX()>droite);
        } else if (typeMonde==2){
            ListeChainee<Couple> list2 = list.selection((Couple o) -> o.getX()==gauche+1 || o.getY()==bas-1);
            int diffX = gauche-droite;
            int diffY = haut-bas;
            Iterator<Couple> it = list2.iterator();
            while (it.hasNext()){
                Couple c = it.next();
                Couple c1 = new Couple(c.getX()-diffX, c.getY()-diffY,c.getNbVoisins());
                list.add(c1);
            }
        }
    }

}