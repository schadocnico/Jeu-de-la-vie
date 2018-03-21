import java.util.Iterator;

public class Generation {

    public static ListeChainee<Couple> newGeneration(ListeChainee<Couple> list){

        ListeChainee<Couple> newGeneration = new ListeChainee<>();
        ListeChainee<Couple> dejaFait = new ListeChainee<>();
        Couple reference;
        //on va se servir d'un maillon de reference pour gerer les naissance des cellules vides aux alentours de celle-ci
        //les cellules de "references" sont les cellules de la liste initiale

        Iterator it = list.iterator();
        while (it.hasNext()) {
            reference = (Couple)it.next() ;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    Couple ATester = new Couple(reference.getX() + i, reference.getY() + j);

                    if (!dejaFait.contains(ATester)){
                        dejaFait.add(ATester); // on exclut les couples dont on a deja verifié les alentours pour le prochain tour de boucle
                        ListeChainee<Couple> listeChainee2 = list.selection(((Couple o) ->
                                (o.getX() == ATester.getX() - 1 || o.getX() == ATester.getX() + 1 || o.getX() == ATester.getX())
                            && (o.getY() == ATester.getY() - 1 || o.getY() == ATester.getY() + 1 || o.getY() == ATester.getY())
                        ));
                        int size=listeChainee2.getSize();
                        boolean estVivant = listeChainee2.contains(ATester);
                        if (estVivant)
                            size--;

                        if (size == 3 || (size==2 && estVivant) ){
                            newGeneration.add(ATester);
                            //la cellule possede au moins 2 voisins, il y a donc une naissance sur cette case
                        }


                    }

                } //if

            }
            // on change le maillon de reference

        } // fin boucle while

        return newGeneration;

    }
}
