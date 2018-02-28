import java.util.Iterator;

public class Generation {

    private ListeChainee<Couple> newGeneration(ListeChainee<Couple> list){

        ListeChainee<Couple> newGeneration = new ListeChainee<>();
        Couple reference = list.getTete().getValeur();
        //on va se servir d'un maillon de reference pour gerer les naissance des cellules vides aux alentours de celle-ci
        //les cellules de "references" sont les cellules de la liste initiale


        Iterator it = list.iterator();
        while (it.hasNext()) {

         for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nbVoisin = 0;

                    if (i != 0 && j != 0) { //i=0 j=0 correspond au Couple ATester
                        Couple ATester = new Couple(reference.getX() + i, reference.getY() + j);

                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {
                                Couple voisin = new Couple(ATester.getX() +k, ATester.getY() +l);
                                if (i != 0 && j != 0 && list.contains(voisin) == true) {
                                    nbVoisin += 1;
                                }
                            }
                        }

                        if (nbVoisin >= 2) {
                            newGeneration.add(ATester);
                            //la cellule possede au moins 2 voisins, il y a donc une naissance sur cette case
                        }

                    } 
                }
            }

            reference = (Couple)it.next() ;
            // on change le maillon de reference

        } // fin boucle while

        return newGeneration;

    }
}
