import java.util.Iterator;

public class Generation {

    public ListeChainee<Couple> newGeneration(ListeChainee<Couple> list){

        ListeChainee<Couple> newGeneration = new ListeChainee<>();
        ListeChainee<Couple> dejaFait = new ListeChainee<>();
        Couple reference = list.premierElement();
        //on va se servir d'un maillon de reference pour gerer les naissance des cellules vides aux alentours de celle-ci
        //les cellules de "references" sont les cellules de la liste initiale


        Iterator it = list.iterator();
        while (it.hasNext()) {

         for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                        Couple ATester = new Couple(reference.getX() + i, reference.getY() + j);

                        if (dejaFait.contains(ATester) != true){
                        dejaFait.add(ATester); // on exclut les couples dont on a deja verifié les alentours pour le prochain tour de boucle

                        ListeChainee<Couple> listeChainee2 = list.selection((o -> {
                                     if (o.getX() == ATester.getX()-1 || o.getX() == ATester.getX()+1 || o.getX() == ATester.getX())
                                         return (o.getY() == ATester.getY()-1 || o.getY() == ATester.getY()+1 || o.getY() == ATester.getY();
                                     else return false;
                                     }));

                        int size=listeChainee2.getSize();
                        if (reference.equals(ATester))
                            size--;

                        if (size >= 2 && size<=3) {
                               newGeneration.add(ATester);
                             //la cellule possede au moins 2 voisins, il y a donc une naissance sur cette case
                        }


                        }

                      } //if

            }

            reference = (Couple)it.next() ;
            // on change le maillon de reference

        } // fin boucle while

        return newGeneration;

    }
}
