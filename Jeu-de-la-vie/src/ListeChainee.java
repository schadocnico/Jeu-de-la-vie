public class ListeChainee<T extends Comparable>{

    private Maillon<T> tete;

    public ListeChainee(){
        this.tete = null;
    }

    public boolean isEmpty(){
        return this.tete == null;
    }

    public void add(T cellule){
        if (isEmpty()) tete.setValeur(cellule);
        Maillon<T> selection = tete;
        //On regarde si le maillon suivant n'est pas null et que la valeur suivante est inferieur
        while (selection.getSuivant()!=null && (selection.getSuivant().getValeur().compareTo(cellule)<=0)) selection=selection.getSuivant();

        selection.setSuivant(new Maillon<>(cellule, selection.getSuivant()));
    }


    private class Maillon<V extends Comparable> {

        private V valeur;
        private Maillon<V> suivant;

        public Maillon(V valeur, Maillon<V> suivant){
            this.valeur=valeur;
            this.suivant=suivant;
        }


        public V getValeur(){
            return valeur;
        }

        public void setValeur(V valeur) {
            this.valeur = valeur;
        }

        public void setSuivant(Maillon<V> suivant) {
            this.suivant = suivant;
        }

        public Maillon<V> getSuivant() {
            return suivant;
        }
    }
}
