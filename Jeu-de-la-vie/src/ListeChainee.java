import java.util.Iterator;

public class ListeChainee<T extends Comparable>{

    public ListeChainee(){
        this.tete = null;
    }

    public boolean isEmpty(){
        return this.tete == null;
    }

    public void add(T cellule){
        if (isEmpty()) {
            tete= new Maillon<>(cellule, null);
            return;
        }
        Maillon<T> selection = tete;
        //On regarde si le maillon suivant n'est pas null et que la valeur suivante est inferieur
        while (selection.getSuivant()!=null && (selection.getSuivant().getValeur().compareTo(cellule)<=0)) selection=selection.getSuivant();

        selection.setSuivant(new Maillon<>(cellule, selection.getSuivant()));
    }

    public ListeChainee<T> groupeCellule(Selection<T> fun){
        Maillon<T> selection = tete;
        ListeChainee<T> liste = new ListeChainee<>();
        while (selection.getSuivant()!=null){
            if(fun.compareTo(selection.valeur))
                liste.add(selection.valeur);
            selection = selection.getSuivant();
        }
        return liste;
    }

    @Override
    public String toString() {
        Maillon<T> selection = tete;
        String str = "[";
        while (selection.getSuivant()!=null){
            str += " " + selection.valeur;
            selection = selection.getSuivant();
        }
        str+= " " + selection.getValeur()+ " ]";
        return str;
    }

    //Classe interne Maillon >>>>>>
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
    //========
}
