import java.util.Iterator;

public class Generation {

    private ListeChainee<Couple> newGeneration(ListeChainee<Couple> list){

        ListeChainee<Couple> newGeneration = new ListeChainee<>();
        Couple ATester = list.getTete().getValeur();
        //on effectue la recherche du nombre de voisin pour ATester

        Iterator it = list.iterator();
        while (it.hasNext()) {

            int nbVoisin = 0;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (i != 0 && j != 0) { //i=0 j=0 correspond au Couple ATester

                        Couple voisin = new Couple(ATester.getX() + i, ATester.getY() + j);

                        if (list.contains(voisin) == true) {
                            nbVoisin += 1;
                        }
                    }
                }
            }

            if (nbVoisin >= 2) {
                newGeneration.add(ATester);
            }

            ATester = (Couple)it.next() ;
            // on change la valeur de ATester

        }

        return newGeneration;

    }
}
